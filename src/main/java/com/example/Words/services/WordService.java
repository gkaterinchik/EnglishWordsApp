package com.example.Words.services;

import com.example.Words.dto.*;
import com.example.Words.entities.Word;
import com.example.Words.model.LearningStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface WordService {
    @NotNull
    List<WordResponse> findAll();
    @NotNull
    WordResponse findById(@NotNull Integer id);
    @NotNull
    List<WordResponse> findByLearningStatus(@NotNull LearningStatus status);
    @NotNull
    WordResponse addWord(@NotNull CreateWordRequest request );
    @NotNull
    WordResponse updateWord( @NotNull CreateWordRequest request) throws Exception;
    ResponseEntity<AddDictionaryResponse> addDictionary(AddDictionaryRequest request);

    void delete(@NotNull Integer id);


}
