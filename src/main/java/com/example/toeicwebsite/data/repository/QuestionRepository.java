package com.example.toeicwebsite.data.repository;

import com.example.toeicwebsite.data.entity.Question;
import com.example.toeicwebsite.data.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long>{

    //this query have problem cause the can't create bean problem


    List<Question> findAllByTopicId(Long topicId);

}
