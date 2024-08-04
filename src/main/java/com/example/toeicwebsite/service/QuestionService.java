package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> getAllQuestionsWithAnswersByTopicId(Long topicId);

    List<Long> getAllQuestionsIDByTopicId(Long topicId);

    PaginationDTO filterQuestion(String keyword, int pageNumber, int pageSize);

}
