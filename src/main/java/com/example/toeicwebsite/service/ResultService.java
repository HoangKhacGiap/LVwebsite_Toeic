package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.MessageResponse;
import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.ResultDTO;

public interface ResultService {
    MessageResponse saveResult(String totalMark);

    PaginationDTO filterResult(String keyword, int pageNumber, int pageSize);

//    PaginationDTO filterResult(String keyword, Long userId, int pageNumber, int pageSize);
}
