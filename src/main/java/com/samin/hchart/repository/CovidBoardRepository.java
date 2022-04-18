package com.samin.hchart.repository;

import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CovidBoardRepository extends JpaRepository<CovidBoard, Long>, SearchBoardRepository {

    // 한개의 로우(object) 내에 Object[ ]로 나옴
    @Query("SELECT b, w FROM CovidBoard b LEFT JOIN b.writer w WHERE b.no =:no")
    Object getCovidBoardWithWriter(@Param("no") Long no);

    @Query("select b, r from CovidBoard b left join Reply r on r.covidBoard = b where b.no =:no")
    List<Object[]> getCovidBoardWithReply(@Param("no") Long no);

    @Query(value = "select b, w, count(r) from CovidBoard b " +
            "left join b.writer w " +
            "left join Reply r on r.covidBoard = b " +
            "group by b",
            countQuery = "select count(b) from CovidBoard b")
    Page<Object[]> getCovidBoardWithReplyCount(Pageable pageable);  // 목록 화면에 필요한 데이터

    @Query("select b, w, count(r) " +
            "from CovidBoard b left join b.writer w " +
            "left outer join Reply r on r.covidBoard = b " +
            "where b.no =:no")
    Object getCovidBoardByNo(@Param("no") Long no);
}
