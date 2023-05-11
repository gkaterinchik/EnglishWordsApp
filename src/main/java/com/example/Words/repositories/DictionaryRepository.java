package com.example.Words.repositories;

import com.example.Words.entities.Dictionary;
import com.example.Words.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary,Integer> {
    Dictionary findDictionaryByDictionaryName(String dictionaryName);
}
