package com.example.toeicwebsite.service.impl;

import com.example.toeicwebsite.data.dto.AnswerDTO;
import com.example.toeicwebsite.data.dto.PaginationDTO;
import com.example.toeicwebsite.data.dto.QuestionDTO;
import com.example.toeicwebsite.data.dto.TopicDTO;
import com.example.toeicwebsite.data.entity.Answer;
import com.example.toeicwebsite.data.entity.Question;
import com.example.toeicwebsite.data.entity.Topic;
import com.example.toeicwebsite.data.repository.AnswerRepository;
import com.example.toeicwebsite.data.repository.QuestionRepository;
import com.example.toeicwebsite.data.repository.TestRepository;
import com.example.toeicwebsite.data.repository.TopicRepository;
import com.example.toeicwebsite.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<QuestionDTO> getAllQuestionsWithAnswersByTopicId(Long topicId) {
        List<Question> questions = questionRepository.findAllByTopicId(topicId);
        return questions.stream().map(this::questionConvertToDTO).collect(Collectors.toList());
    }

    public List<AnswerDTO> getAllAnswerByQuestionId(Long questionId) {
        List<Answer> answers = answerRepository.findAllByQuestionId(questionId);
        return answers.stream().map(this::answerConvertToDTO).collect(Collectors.toList());
    }

    private QuestionDTO questionConvertToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setName(question.getName());
        questionDTO.setTopicId(question.getTopic().getId());
        List<AnswerDTO> answerDTOs = getAllAnswerByQuestionId(question.getId());
        questionDTO.setAnswers(answerDTOs);

        return questionDTO;
    }

    private AnswerDTO answerConvertToDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setId(answer.getId());
        answerDTO.setContent(answer.getContent());
        answerDTO.setCorrectAnswer(answer.isCorrectAnswer());
        answerDTO.setQuestionId(answer.getQuestion().getId());

        return answerDTO;
    }
}
