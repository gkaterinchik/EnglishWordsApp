package com.example.Words.dto;

import lombok.Data;

@Data
public class AddDictionaryResponse {
    private int AddedWordsCount;
    private int WordsInDictionary;
    private String dictionaryName;
}
