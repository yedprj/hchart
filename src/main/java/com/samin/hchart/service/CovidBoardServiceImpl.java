package com.samin.hchart.service;

import com.samin.hchart.dto.CovidBoardDTO;
import com.samin.hchart.dto.PageRequestDTO;
import com.samin.hchart.dto.PageResultDTO;
import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.repository.CovidBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor    // 의존성 자동 주입
public class CovidBoardServiceImpl implements CovidBoardService{

    private final CovidBoardRepository repository;

    @Override
    public Long register(CovidBoardDTO dto) {

        log.info("DTO-------------------------");
        log.info(dto);

        CovidBoard entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getNo();
    }

    // 게시판 조회
    @Override
    public CovidBoardDTO read(Long no) {

        Optional<CovidBoard> result = repository.findById(no);

        return result.map(this::entityToDto).orElse(null);
    }

    // 게시글 삭제
    @Override
    public void remove(Long no) {

        repository.deleteById(no);
    }

    // 게시글 수정 (제목과 내용만)
    @Override
    public void modify(CovidBoardDTO dto) {

        Optional<CovidBoard> result = repository.findById(dto.getNo());

        if (result.isPresent()){
            CovidBoard entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }

    @Override
    public PageResultDTO<CovidBoardDTO, CovidBoard> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());

        Page<CovidBoard> result = repository.findAll(pageable);

        Function<CovidBoard, CovidBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}
