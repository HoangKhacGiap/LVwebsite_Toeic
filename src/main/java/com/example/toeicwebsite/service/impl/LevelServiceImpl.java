package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.LevelDTO;
import com.example.toeicwebsite.data.dto.MessageResponse;
import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.entity.Level;
import com.example.toeicwebsite.data.entity.Skill;
import com.example.toeicwebsite.data.mapper.LevelMapper;
import com.example.toeicwebsite.data.repository.LevelRepository;
import com.example.toeicwebsite.exception.ConflictException;
import com.example.toeicwebsite.exception.ResourceNotFoundException;
import com.example.toeicwebsite.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private LevelMapper levelMapper;
    @Override
    public MessageResponse createLevel(LevelDTO levelDTO) {
        if (levelRepository.findByName(levelDTO.getName()).isPresent()) {
            throw new ConflictException(Collections.singletonMap("skill name", levelDTO.getName()));
        } else {
            Level level = levelMapper.toEntity(levelDTO);
            levelRepository.save(level);
        }
        return new MessageResponse(HttpServletResponse.SC_OK, "Tao skill thanh cong");
    }

    @Override
    public PaginationDTO filterLevel(String keyword, int pageNumber, int pageSize) {
        Page<Level> page = levelRepository.filterLevel(keyword, PageRequest.of(pageNumber, pageSize));
        List<LevelDTO> list = new ArrayList<>();

        for (Level level: page.getContent()) {

            LevelDTO levelDTO = levelMapper.toDTO(level);

            list.add(levelDTO);
        }
        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

    }

    @Override
    public MessageResponse updateLevel(LevelDTO levelDTO) {
        Level level = levelRepository.findById(levelDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("level id", levelDTO.getId()))
        );

        level.setName(levelDTO.getName());
        levelRepository.save(level);

        return new MessageResponse(HttpServletResponse.SC_OK, "update skill thanh cong");
    }
}
