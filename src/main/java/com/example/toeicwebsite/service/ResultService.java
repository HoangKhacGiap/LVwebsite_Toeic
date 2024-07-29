package com.example.toeicwebsite.service;

import com.example.toeicwebsite.data.dto.MessageResponse;
import com.example.toeicwebsite.data.dto.ResultDTO;

public interface ResultService {
    MessageResponse saveResult(String totalMark);
}
