package com.samin.hchart.service;

import com.samin.hchart.dto.CovidBoardDTO;
import com.samin.hchart.dto.PageRequestDTO;
import com.samin.hchart.dto.PageResultDTO;
import com.samin.hchart.entity.CovidBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CovidBoardServiceTests {

    @Autowired
    private CovidBoardService service;

    @Test
    public void testRegister() {

        CovidBoardDTO covidBoardDTO = CovidBoardDTO.builder()
                .title("Sample Title.....")
                .content("Sample Content.....")
                .writer("user0")
                .build();

        System.out.println(service.register(covidBoardDTO));
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<CovidBoardDTO, CovidBoard> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("----------------------------------------------");
        for (CovidBoardDTO covidBoardDTO : resultDTO.getDtoList()) {
            System.out.println(covidBoardDTO);
        }

        System.out.println("==================================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
