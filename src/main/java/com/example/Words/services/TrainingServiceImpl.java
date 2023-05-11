package com.example.Words.services;

import com.example.Words.dto.WordResponse;
import com.example.Words.entities.Word;
import com.example.Words.exceptions.ElementNotFound;
import com.example.Words.model.LearningStatus;
import com.example.Words.repositories.WordRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {
    WordRepository wordRepository;

    public TrainingServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<WordResponse> learn()throws RuntimeException {

        List<Word> list=wordRepository.findByLearningStatusAndUser_Username(LearningStatus.NEW, SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(list);
        if(list.size()<1)
            throw new ElementNotFound("No such elements");
        return list.stream().map(this::buildWordResponse).collect(Collectors.toList());


    }

    @Override
    public List<WordResponse> repeat() {

        return wordRepository.findByLearningStatus(LearningStatus.NEW).stream().map(this::buildWordResponse).collect(Collectors.toList());
    }

     @NotNull
    private WordResponse buildWordResponse(@NotNull Word word) {
        System.out.println("LearningStatus=   "+word.getLearningStatus());
        return new WordResponse()
                .setId(word.getId())
                .setWord(word.getWord())
                .setUsername(word.getUser().getUsername())
                .setStatus(word.getLearningStatus())
                .setTranslate(word.getTranslate())
                .setContext(word.getContext());
    }
}
