package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.*;
import com.example.toeicwebsite.data.entity.Part;
import com.example.toeicwebsite.data.entity.Skill;
import com.example.toeicwebsite.data.mapper.PartMapper;
import com.example.toeicwebsite.data.repository.PartRepository;
import com.example.toeicwebsite.data.repository.SkillRepository;
import com.example.toeicwebsite.exception.ConflictException;
import com.example.toeicwebsite.exception.ResourceNotFoundException;
import com.example.toeicwebsite.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private PartMapper partMapper;
    @Autowired
    private SkillRepository skillRepository;
    @Override
    public MessageResponse createPart(PartDTO partDTO) {
        Skill skill = skillRepository.findById(partDTO.getSkillId()).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "kỹ năng không tồn tại")));
        if (partRepository.findByName(partDTO.getName()).isPresent()) {
            throw new ConflictException(Collections.singletonMap("part name", partDTO.getName()));
        }
        else {
            Part part = partMapper.toEntity(partDTO);
            part.setSkill(skill);
            partRepository.save(part);
        }
        return new MessageResponse(HttpServletResponse.SC_OK, "tạo part thành công");
    }

    @Override
    public PaginationDTO filterPart(String keyword, int pageNumber, int pageSize) {
        Page<Part> page = partRepository.filterPart(keyword, PageRequest.of(pageNumber, pageSize));
        List<PartShowDTO> list = new ArrayList<>();

        for (Part part: page.getContent()) {

            PartShowDTO partDTO = partMapper.toDTO(part);
            list.add(partDTO);
        }
        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

    }
}
