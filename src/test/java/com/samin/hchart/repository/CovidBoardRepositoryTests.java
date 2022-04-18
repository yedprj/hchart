package com.samin.hchart.repository;

import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class CovidBoardRepositoryTests {

    @Autowired
    private CovidBoardRepository covidBoardRepository;

    @Test
    public void insertCovidBoard() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder().email("user"+i+"@samin.com").build();

            CovidBoard covidBoard = CovidBoard.builder()
                    .title("Test Title....." + i)
                    .content("Test Content....." + i)
                    .writer(member)
                    .build();

            covidBoardRepository.save(covidBoard);
        });
    }

    @Test
    public void testRead1() {

        Optional<CovidBoard> result = covidBoardRepository.findById(100L);

        CovidBoard covidBoard = result.get();

        System.out.println(covidBoard);
        System.out.println(covidBoard.getWriter());
    }

    @Test
    public void testReadWithWriter() {

        Object result = covidBoardRepository.getCovidBoardWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("--------------------------------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGetCovidBoardWithReply() {

        List<Object[]> result = covidBoardRepository.getCovidBoardWithReply(100L);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("no").descending());

        Page<Object[]> result = covidBoardRepository.getCovidBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;

            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3() {

        Object result = covidBoardRepository.getCovidBoardByNo(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearch1() {

        covidBoardRepository.search1();
    }

    @Test
    public void testSearchPage() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("no").descending().and(Sort.by("title").ascending()));

        Page<Object[]> result = covidBoardRepository.searchPage("t", "2", pageable);
    }
}
