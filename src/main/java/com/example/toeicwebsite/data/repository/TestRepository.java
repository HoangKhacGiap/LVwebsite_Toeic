package com.example.toeicwebsite.data.repository;

import com.example.toeicwebsite.data.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long>{
}
