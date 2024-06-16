package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.PaginationDTO;


public interface TopicService {
    PaginationDTO filterTopic(String levelName, String partName, int numberOfTopic, int pageNumber, int pageSize);

}
