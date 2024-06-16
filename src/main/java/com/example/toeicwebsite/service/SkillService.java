package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.MessageResponse;
import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.SkillDTO;

public interface SkillService {
    MessageResponse createSkill(SkillDTO skillDTO);
    PaginationDTO filterSkill(String keyword, int pageNumber, int pageSize);
}
