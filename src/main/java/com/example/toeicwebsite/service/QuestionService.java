package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> getAllQuestionsWithAnswersByTopicId(Long topicId);
}
