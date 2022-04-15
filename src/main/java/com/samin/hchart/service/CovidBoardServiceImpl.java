package com.samin.hchart.service;

import com.samin.hchart.dto.CovidBoardDTO;
import com.samin.hchart.dto.PageRequestDTO;
import com.samin.hchart.dto.PageResultDTO;
import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.Member;
import com.samin.hchart.repository.CovidBoardRepository;
import com.samin.hchart.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class CovidBoardServiceImpl implements CovidBoardService{

    private final CovidBoardRepository repository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(CovidBoardDTO dto) {

        log.info(dto);

        CovidBoard covidBoard = dtoToEntity(dto);

        repository.save(covidBoard);

        return covidBoard.getNo();
    }

    @Override
    public PageResultDTO<CovidBoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], CovidBoardDTO> fn = (en -> entityToDTO((CovidBoard) en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> result = repository.getCovidBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("no").descending()))
;
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public CovidBoardDTO get(Long no) {

        Object result = repository.getCovidBoardByNo(no);

        Object[] arr = (Object[]) result;

        return entityToDTO((CovidBoard)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long no) {    // 삭제 기능 구현, 트랜젝션 추가
        replyRepository.deleteBtyNo(no);
        repository.deleteById(no);
    }

    @Transactional
    @Override
    public void modify(CovidBoardDTO covidBoardDTO) {

        CovidBoard covidBoard = repository.getOne(covidBoardDTO.getNo());

        covidBoard.changTitle(covidBoardDTO.getTitle());
        covidBoard.changeContent(covidBoardDTO.getContent());

        repository.save(covidBoard);
    }
}
