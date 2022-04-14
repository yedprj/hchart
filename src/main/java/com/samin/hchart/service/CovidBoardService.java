package com.samin.hchart.service;

import com.samin.hchart.dto.CovidBoardDTO;
import com.samin.hchart.dto.PageRequestDTO;
import com.samin.hchart.dto.PageResultDTO;
import com.samin.hchart.entity.CovidBoard;

public interface CovidBoardService {

    Long register(CovidBoardDTO dto);

    // 게시판 조회
    CovidBoardDTO read(Long no);

    // 게시글 삭제
    void remove(Long no);

    // 게시글 수정
    void modify(CovidBoardDTO dto);

    PageResultDTO<CovidBoardDTO, CovidBoard> getList(PageRequestDTO requestDTO);

    default CovidBoard dtoToEntity(CovidBoardDTO dto) {
        CovidBoard entity = CovidBoard.builder()
                .no(dto.getNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    default CovidBoardDTO entityToDto(CovidBoard entity) {

        CovidBoardDTO dto = CovidBoardDTO.builder()
                .no(entity.getNo())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
