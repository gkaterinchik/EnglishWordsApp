package com.example.Words.dto;

import com.example.Words.entities.User;
import com.example.Words.model.LearningStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateWordResponse {
    private Integer id;
    private String word;
    private String context;
    private String translate;
    private LearningStatus status;
    private User user;
}
