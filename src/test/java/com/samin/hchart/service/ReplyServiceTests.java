package com.samin.hchart.service;

import com.samin.hchart.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService service;

    @Test
    public void testGetList() {

        Long no = 100L;

        List<ReplyDTO> replyDTOList = service.getList(no);

        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }
}
