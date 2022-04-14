package com.samin.hchart.repository;

import com.samin.hchart.entity.CovidBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CovidBoardRepository extends JpaRepository<CovidBoard, Long>, QuerydslPredicateExecutor<CovidBoard> {
}
