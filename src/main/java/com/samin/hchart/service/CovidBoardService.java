package com.samin.hchart.service;

import com.samin.hchart.dto.CovidBoardDTO;
import com.samin.hchart.dto.PageRequestDTO;
import com.samin.hchart.dto.PageResultDTO;
import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.Member;

public interface CovidBoardService {

    Long register(CovidBoardDTO dto);

    PageResultDTO<CovidBoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 게시물 조회
    CovidBoardDTO get(Long no);

    // 게시물 삭제
    void removeWithReplies(Long no);

    // 게시글 수정
    void modify(CovidBoardDTO covidBoardDTO);


    default CovidBoard dtoToEntity(CovidBoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        CovidBoard covidBoard = CovidBoard.builder()
                .no(dto.getNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return covidBoard;
    }

    default CovidBoardDTO entityToDTO(CovidBoard covidBoard, Member member, Long replyCount){

        CovidBoardDTO covidBoardDTO = CovidBoardDTO.builder()
                .no(covidBoard.getNo())
                .title(covidBoard.getTitle())
                .content(covidBoard.getContent())
                .regDate(covidBoard.getRegDate())
                .modDate(covidBoard.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return covidBoardDTO;
    }
}
