package com.samin.hchart.service;

import com.samin.hchart.dto.ReplyDTO;
import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.Reply;
import com.samin.hchart.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long no) {

        List<Reply> result = replyRepository.getRepliesByCovidBoardOrderByRno(CovidBoard.builder().no(no).build());

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {

        replyRepository.deleteById(rno);
    }
}
