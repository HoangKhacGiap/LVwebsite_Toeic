package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.TopicDTO;
import com.example.toeicwebsite.data.entity.Topic;
import com.example.toeicwebsite.data.repository.QuestionRepository;
import com.example.toeicwebsite.data.repository.TestRepository;
import com.example.toeicwebsite.data.repository.TopicRepository;
import com.example.toeicwebsite.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TestRepository testRepository;


//    @Override
//    public PaginationDTO filterTopic(String levelName, String partName, int numberOfTopic, int pageNumber, int pageSize) {
//        Page<Topic> page = topicRepository.filterTopic(levelName, partName, numberOfTopic, PageRequest.of(pageNumber, pageSize));
//        List<TopicDTO> list = new ArrayList<>();
//
//        for (Topic topic: page.getContent()) {
//
//            TopicDTO topicDTO = new TopicDTO();
//
//            topicDTO.setId(topic.getId());
//            topicDTO.setAudioName(topic.getAudio_name());
//            topicDTO.setImageName(topic.getImage_name());
//            topicDTO.setPathAudio(topic.getAudio_path());
//            topicDTO.setPathImage(topic.getImage_path());
//            topicDTO.setLevelId(topic.getLevel().getId());
//            topicDTO.setPartId(topic.getPart().getId());
//
//            list.add(topicDTO);
//        }
//        return new PaginationDTO(list, page.isFirst(), page.isLast(),
//                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());
//
//    }
}
