package com.samin.hchart.repository;

import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {

            // 1부터 100까지 임의의 번호
            long no = (long)(Math.random() * 100) + 1;

            CovidBoard covidBoard = CovidBoard.builder().no(no).build();

            Reply reply = Reply.builder()
                    .text("Reply......." + i)
                    .covidBoard(covidBoard)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    @Transactional
    public void readReply1() {

        Optional<Reply> result = replyRepository.findById(1L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getCovidBoard());
    }

    @Test
    public void testListByBoard(){

        List<Reply> replyList = replyRepository.getRepliesByCovidBoardOrderByRno(CovidBoard.builder().no(97L).build());

        replyList.forEach(reply -> System.out.println(reply));
    }
}
