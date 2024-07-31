package com.example.toeicwebsite.controller;

import com.example.toeicwebsite.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Admin')")
    @GetMapping("/filterTopic")
    public ResponseEntity<?> filterTopic(@RequestParam(defaultValue = "0") int pageNumber,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "") String keyword) {

        return ResponseEntity.ok(topicService.filterTopic(keyword, pageNumber, pageSize));
    }
}
