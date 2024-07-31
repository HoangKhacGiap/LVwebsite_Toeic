package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.SkillDTO;
import com.example.toeicwebsite.data.dto.TopicDTO;
import com.example.toeicwebsite.data.entity.Skill;
import com.example.toeicwebsite.data.entity.Structure;
import com.example.toeicwebsite.data.entity.Topic;
import com.example.toeicwebsite.data.repository.StructureRepository;
import com.example.toeicwebsite.data.repository.TopicRepository;
import com.example.toeicwebsite.exception.ResourceNotFoundException;
import com.example.toeicwebsite.service.QuestionService;
import com.example.toeicwebsite.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private QuestionService questionService;

    @Override
    public List<TopicDTO> getTopicsByStructure(Long structureId) {
        Structure structure = structureRepository.findById(structureId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "structure không tồn tại")));

        List<Topic> topics = topicRepository.findAllByLevelNameAndPartId
                (structure.getPart().getId(), structure.getLevel_of_topic());
        return topics.stream()
                .limit(structure.getNumber_of_topic())
                .map(this::topicConvertToDTO)
                .collect(Collectors.toList());
    }

    public List<Long> getListTopicsIDByStructure(Long structureId) {
        Structure structure = structureRepository.findById(structureId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "structure không tồn tại")));

        List<Topic> topics = topicRepository.findAllByLevelNameAndPartId
                (structure.getPart().getId(), structure.getLevel_of_topic());
        return topics.stream()
                .limit(structure.getNumber_of_topic())

                .map(Topic::getId)
                .collect(Collectors.toList());
    }

    @Override
    public PaginationDTO filterTopic(String keyword, int pageNumber, int pageSize) {
        Page<Topic> page = topicRepository.filterTopic(keyword, PageRequest.of(pageNumber, pageSize));
        List<TopicDTO> list = new ArrayList<>();

        for (Topic topic: page.getContent()) {
//            SkillDTO skillDTO = skillMapper.toDTO(skill);
            TopicDTO topicDTO = new TopicDTO();

            topicDTO.setId(topic.getId());
            topicDTO.setName(topic.getName());
            topicDTO.setContent(topic.getContent());
            topicDTO.setImageName(topic.getImage_name());
            topicDTO.setAudioName(topic.getAudio_name());
            topicDTO.setPathImage(topic.getImage_path());
            topicDTO.setPathAudio(topic.getAudio_path());
            topicDTO.setPartId(topic.getPart().getId());
            topicDTO.setLevelId(topic.getLevel().getId());


            list.add(topicDTO);
        }
        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

    }

    private TopicDTO topicConvertToDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();

        topicDTO.setId(topic.getId());
        topicDTO.setName(topic.getName());
        topicDTO.setContent(topic.getContent());
        topicDTO.setImageName(topic.getImage_name());
        topicDTO.setAudioName(topic.getAudio_name());
        topicDTO.setPathImage(topic.getImage_path());
        topicDTO.setPathAudio(topic.getAudio_path());
        topicDTO.setPartId(topic.getPart().getId());
        topicDTO.setLevelId(topic.getLevel().getId());
        topicDTO.setQuestions(questionService.getAllQuestionsWithAnswersByTopicId(topic.getId()));

        return topicDTO;
    }

}
