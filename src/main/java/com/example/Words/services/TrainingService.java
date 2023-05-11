package com.example.Words.services;

import com.example.Words.dto.WordResponse;

import java.util.List;

public interface TrainingService {
     List<WordResponse> learn();
     List<WordResponse> repeat();
}
