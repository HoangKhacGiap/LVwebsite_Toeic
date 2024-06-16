package com.example.toeicwebsite.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String content;
    private Long topicId;
}
