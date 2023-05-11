package com.example.Words.repositories;

import com.example.Words.entities.User;
import com.example.Words.entities.Word;
import com.example.Words.model.LearningStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WordRepository extends JpaRepository<Word,Integer> {
    List<Word> findFirstByWord(String word);
    void deleteByWord(String word);
    Set<Word> findByUser_Username(String name);
    List<Word>findByLearningStatus(LearningStatus learningStatus);
    List<Word> findByWordAndUser_Username(String word, String username);
     List<Word> findByLearningStatusAndUser_Username(LearningStatus learningStatus, String username);
     Word findTopByWordAndUser_Username(String word, String username);


    List<Word> findByWord(String word);
}
