package com.samin.hchart.repository.search;

import com.samin.hchart.entity.CovidBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

    CovidBoard search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
