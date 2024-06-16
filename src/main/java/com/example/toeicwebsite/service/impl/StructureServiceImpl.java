package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.MessageResponse;
import com.example.toeicwebsite.data.dto.StructureDTO;
import com.example.toeicwebsite.data.entity.KindOfStructure;
import com.example.toeicwebsite.data.entity.Level;
import com.example.toeicwebsite.data.entity.Part;
import com.example.toeicwebsite.data.entity.Structure;
import com.example.toeicwebsite.data.repository.KindOfStructureRepository;
import com.example.toeicwebsite.data.repository.LevelRepository;
import com.example.toeicwebsite.data.repository.PartRepository;
import com.example.toeicwebsite.data.repository.StructureRepository;
import com.example.toeicwebsite.exception.ConflictException;
import com.example.toeicwebsite.exception.ExceptionCustom;
import com.example.toeicwebsite.exception.ResourceNotFoundException;
import com.example.toeicwebsite.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StructureServiceImpl implements StructureService {
    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private KindOfStructureRepository kindOfStructureRepository;

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Override
    public Long saveStructure(List<StructureDTO> structureDTOs) {
        KindOfStructure kindOfStructure = new KindOfStructure();
        Set<Long> partIds = new HashSet<>();
        List<Structure> structures = structureDTOs.stream().map(dto -> {

            // Kiểm tra xem partId đã tồn tại trong Set chưa
            if (!partIds.add(dto.getPart_id())) {
                throw new ConflictException(Collections.singletonMap("message", "cấu trúc bạn lưu bị trùng lặp"));
            }


            Part part = partRepository.findById(dto.getPart_id()).orElseThrow(
                    () -> new ResourceNotFoundException(Collections.singletonMap("message", "part không tồn tại")));
            Level level = levelRepository.findByName(dto.getLevel_of_topic()).orElseThrow(
                    () -> new ResourceNotFoundException(Collections.singletonMap("message", "level không tồn tại")));
            Structure structure = new Structure();
            structure.setName(dto.getName());
            structure.setNumber_of_topic(dto.getNumber_of_topic());
            structure.setLevel_of_topic(dto.getLevel_of_topic());
            structure.setPart(part);
            structure.setKinfOfStructure(kindOfStructure);
            return structure;
        }).collect(Collectors.toList());

        kindOfStructureRepository.save(kindOfStructure);
        structureRepository.saveAll(structures);

        return kindOfStructure.getId();
    }
}
