package com.samin.hchart.service;

import com.samin.hchart.dto.CovidBoardDTO;
import com.samin.hchart.dto.PageRequestDTO;
import com.samin.hchart.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CovidBoardServiceTests {

    @Autowired
    private CovidBoardService covidBoardService;

    @Test
    public void testRegister() {

        CovidBoardDTO dto = CovidBoardDTO.builder()
                .title("TEST..........")
                .content("CONTENT TEST..........")
                .writerEmail("user55@samin.com")
                .build();

        Long no = covidBoardService.register(dto);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<CovidBoardDTO, Object[]> result = covidBoardService.getList(pageRequestDTO);

        for (CovidBoardDTO covidBoardDTO : result.getDtoList()) {
            System.out.println(covidBoardDTO);
        }
    }

    @Test
    public void testGet() {

        Long no = 100L;

        CovidBoardDTO covidBoardDTO = covidBoardService.get(no);

        System.out.println(covidBoardDTO);
    }

    @Test
    public void testRemove() {
        Long no = 1L;

        covidBoardService.removeWithReplies(no);
    }

    @Test
    public void testModify() {

        CovidBoardDTO covidBoardDTO = CovidBoardDTO.builder()
                .no(100L)
                .title("제목 변경합니다.")
                .content("내용 변경합니다.")
                .build();

        covidBoardService.modify(covidBoardDTO);
    }
}
