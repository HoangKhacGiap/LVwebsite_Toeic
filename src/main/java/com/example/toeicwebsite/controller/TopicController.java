package com.example.toeicwebsite.controller;

import com.example.toeicwebsite.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/{structureId}")
    public ResponseEntity<?> getTopicsByStructureId(@PathVariable Long structureId) {
        return ResponseEntity.ok(topicService.getTopicsByStructure(structureId));
    }
}
