package com.example.Words.controllers;

import com.example.Words.dto.WordResponse;
import com.example.Words.entities.Word;
import com.example.Words.repositories.DictionaryRepository;
import com.example.Words.repositories.UserRepository;
import com.example.Words.repositories.WordRepository;
import com.example.Words.services.TrainingServiceImpl;
import com.example.Words.services.WordServiceImpl;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RestController
@RequestMapping("/api/v1/training")
public class TrainingController {
    TrainingServiceImpl trainingService;
    WordRepository wordRepository;
    UserRepository userRepository;
    WordServiceImpl wordService;
    DictionaryRepository dictionaryRepository;


    public TrainingController(TrainingServiceImpl trainingService, WordRepository wordRepository, UserRepository userRepository,WordServiceImpl wordService,DictionaryRepository dictionaryRepository) {
        this.trainingService = trainingService;
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.wordService=wordService;
        this.dictionaryRepository=dictionaryRepository;
    }

    @GetMapping("/learn")
    public List<WordResponse> learn(){
        return trainingService.learn();

    }
    @GetMapping("/repeat")
    public List<WordResponse> repeat(){
        return dictionaryRepository.findDictionaryByDictionaryName("Dict1").getWords().stream()
                .map(this::buildWordResponse)
                .collect(Collectors.toList());

    }
    private WordResponse buildWordResponse(@NotNull Word word) {
        System.out.println("LearningStatus=   "+word.getLearningStatus());
        return new WordResponse()
                .setId(word.getId())
                .setWord(word.getWord())
                .setUsername(word.getUser().getUsername())
                .setStatus(word.getLearningStatus())
                .setTranslate(word.getTranslate())
                .setContext(word.getContext())
                .setLastStudy(word.getLastStudy());
    }
}
