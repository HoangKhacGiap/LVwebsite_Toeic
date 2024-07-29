package com.example.toeicwebsite.data.repository;

import com.example.toeicwebsite.data.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
