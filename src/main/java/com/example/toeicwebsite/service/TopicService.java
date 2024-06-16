package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.TopicDTO;

import java.util.List;


public interface TopicService {
    List<TopicDTO> getTopicsByStructure(Long structureId);
}
