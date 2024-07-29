package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.MessageResponse;
import com.example.toeicwebsite.data.dto.ResultDTO;
import com.example.toeicwebsite.data.entity.Result;
import com.example.toeicwebsite.data.entity.User;
import com.example.toeicwebsite.data.repository.ResultRepository;
import com.example.toeicwebsite.data.repository.UserRepository;
import com.example.toeicwebsite.exception.ResourceNotFoundException;
import com.example.toeicwebsite.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Override
    public MessageResponse saveResult(String totalMark) {
        Date currentDate = new Date();
        User userCurrent = getNguoiDungByToken();
        String status = "Hoan thanh";

        Result result = new Result();
//        result.setId(resultDTO.getId());
        result.setStatus(status);
        result.setTotalMark(totalMark);
        result.setCreateAt(currentDate);
        result.setUser(userCurrent);

        resultRepository.save(result);

        return new MessageResponse(HttpServletResponse.SC_OK, "luu ket qua thanh cong");
    }

    public User getNguoiDungByToken() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEmail = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "Tai khoan nay khong ton tai")));

        return userRepository.findById(userEmail.getId()).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "nguoi dung nay khong ton tai"))
        );
    }
}
