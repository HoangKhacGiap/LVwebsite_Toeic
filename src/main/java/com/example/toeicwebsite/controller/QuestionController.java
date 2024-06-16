package com.example.toeicwebsite.controller;

import com.example.toeicwebsite.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/{topicId}")
    public ResponseEntity<?> getAllQuestionsWithAnswersByTopicId(@PathVariable Long topicId) {
        return ResponseEntity.ok(questionService.getAllQuestionsWithAnswersByTopicId(topicId));
    }
}
