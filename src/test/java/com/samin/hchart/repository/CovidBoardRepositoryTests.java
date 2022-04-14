package com.samin.hchart.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.QCovidBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class CovidBoardRepositoryTests {

    @Autowired
    private CovidBoardRepository covidBoardRepository;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            CovidBoard covidBoard = CovidBoard.builder()
                    .title("게시판 TEST....." + i)
                    .content("게시판 TEST 내용부분....." + i)
                    .writer("user" + (i % 10))
                    .build();

            System.out.println(covidBoardRepository.save(covidBoard));
        });
    }

    @Test
    public void updateTest() {

        Optional<CovidBoard> result = covidBoardRepository.findById(100L);

        if(result.isPresent()) {

            CovidBoard covidBoard = result.get();

            covidBoard.changeTitle("게시판 제목 변경 TEST.....");
            covidBoard.changeContent("게시판 내용부분 변경 TEST.....");

            covidBoardRepository.save(covidBoard);
        }
    }

    @Test
    public void testQuery1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("no").descending());

        QCovidBoard qCovidBoard = QCovidBoard.covidBoard;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qCovidBoard.title.contains(keyword);

        builder.and(expression);

        Page<CovidBoard> result = covidBoardRepository.findAll(builder, pageable);

        result.stream().forEach(covidBoard -> {
            System.out.println(covidBoard);
        });
    }

    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("no").descending());

        QCovidBoard qCovidBoard = QCovidBoard.covidBoard;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qCovidBoard.title.contains(keyword);

        BooleanExpression exContent = qCovidBoard.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qCovidBoard.no.gt(0L));

        Page<CovidBoard> result = covidBoardRepository.findAll(builder, pageable);

        result.stream().forEach(covidBoard -> {
            System.out.println(covidBoard);
        });
    }
}
