package com.samin.hchart.repository;

import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 게시물 삭제 처리
    @Modifying
    @Query("delete from Reply r where r.covidBoard.no =:no")
    void deleteBtyNo(Long no);

    // 게시물 목록 가져오기
    List<Reply> getRepliesByCovidBoardOrderByRno(CovidBoard covidBoard);
}
