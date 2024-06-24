package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.TopicDTO;

import java.util.List;


public interface TopicService {
    List<TopicDTO> getTopicsByStructure(Long structureId);

    List<Long> getListTopicsIDByStructure(Long structureId);
}
