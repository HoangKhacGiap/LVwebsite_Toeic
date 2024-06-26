package com.example.toeicwebsite.controller;

import com.example.toeicwebsite.data.dto.StructureDTO;
import com.example.toeicwebsite.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StructureController {
    @Autowired
    private StructureService structureService;

    @PostMapping("/saveStructure")
    public ResponseEntity<?> saveStructure(@Valid @RequestBody List<StructureDTO> structureDTOs) {
        return ResponseEntity.ok(structureService.saveStructure(structureDTOs));
    }
}
