package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.MessageResponse;
import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.StructureDTO;
import com.example.toeicwebsite.data.dto.TopicDTO;
import com.example.toeicwebsite.data.entity.*;
import com.example.toeicwebsite.data.repository.*;
import com.example.toeicwebsite.exception.ConflictException;
import com.example.toeicwebsite.exception.ExceptionCustom;
import com.example.toeicwebsite.exception.ResourceNotFoundException;
import com.example.toeicwebsite.service.StructureService;
import com.example.toeicwebsite.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
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

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserRepository userRepository;
    public Long saveStructure(List<StructureDTO> structureDTOs) {
        User user = getNguoiDungByToken();

        KindOfStructure kindOfStructure = new KindOfStructure();
        kindOfStructure.setName(user.getEmail());
        kindOfStructure.setStatus("Thành Công");

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
            structure.setName(user.getEmail());
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

    @Override
    public List<StructureDTO> getStructureByKindStructureId(Long kindStructureId) {
        KindOfStructure kindOfStructure = kindOfStructureRepository.findById(kindStructureId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "kindOfStructure không tồn tại")));

        List<Structure> structures = structureRepository.findAllByKinfOfStructureId(kindStructureId);
        if (structures.isEmpty()) {
            throw new ResourceNotFoundException(Collections.singletonMap("message", "không có cấu trúc nào"));
        }
//                (structure.getPart().getId(), structure.getLevel_of_topic());
        return structures.stream()
//                .limit(structure.getNumber_of_topic())
                .map(this::structureConvertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getListStructureIDByKindStructure(Long kindStructureId) {
        KindOfStructure kindOfStructure = kindOfStructureRepository.findById(kindStructureId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "Kind structure không tồn tại")));

        List<Structure> structures = structureRepository.findAllByKinfOfStructureId(kindOfStructure.getId());
        return structures.stream()
                .map(Structure::getId)
                .collect(Collectors.toList());
    }

    @Override
    public long countStructureCreate() {
        return structureRepository.countStructureCreate();
    }

    @Override
    public PaginationDTO filterStructure(String keyword, int pageNumber, int pageSize) {
        Page<Structure> page = structureRepository.filterStructure(keyword, PageRequest.of(pageNumber, pageSize));
        List<StructureDTO> list = new ArrayList<>();

        for (Structure structure: page.getContent()) {
            StructureDTO structureDTO = new StructureDTO();

            structureDTO.setId(structure.getId());
            structureDTO.setName(structure.getName());
            structureDTO.setNumber_of_topic(structure.getNumber_of_topic());
            structureDTO.setLevel_of_topic(structure.getLevel_of_topic());

            structureDTO.setPart_id(structure.getPart().getId());

            structureDTO.setTopics(topicService.getTopicsByStructure(structure.getId()));

            list.add(structureDTO);
        }
        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());
    }

    private StructureDTO structureConvertToDTO(Structure structure) {
        StructureDTO structureDTO = new StructureDTO();

        structureDTO.setId(structure.getId());
        structureDTO.setName(structure.getName());
        structureDTO.setNumber_of_topic(structure.getNumber_of_topic());
        structureDTO.setLevel_of_topic(structure.getLevel_of_topic());
        structureDTO.setPart_id(structure.getPart().getId());
        structureDTO.setTopics(topicService.getTopicsByStructure(structure.getId()));

        return structureDTO;
    }
    public User getNguoiDungByToken() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEmail = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "Tai khoan nay khong ton tai")));

        return userRepository.findById(userEmail.getId()).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "nguoi dung nay khong ton tai"))
        );
    }
}
