package com.example.toeicwebsite.controller;

import com.example.toeicwebsite.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/filterTopic")
    public ResponseEntity<?> filterTopic(
                                        @RequestParam(defaultValue = "easy") String levelName,
                                        @RequestParam(defaultValue = "part_5_reading") String partName,
                                        @RequestParam(defaultValue = "2") int numberOfTopic,
                                        @RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = "100") int pageSize) {
        return ResponseEntity.ok(topicService.filterTopic(levelName, partName, numberOfTopic, pageNumber, pageSize));
    }
}
