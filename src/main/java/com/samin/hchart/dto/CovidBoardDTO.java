package com.samin.hchart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CovidBoardDTO {

    private Long no;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;
}
