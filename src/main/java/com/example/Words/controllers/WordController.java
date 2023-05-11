package com.example.Words.controllers;

import com.example.Words.dto.CreateWordRequest;
import com.example.Words.dto.WordResponse;
import com.example.Words.services.WordServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    WordServiceImpl wordServiceImpl;

    public WordController(WordServiceImpl wordServiceImpl) {
        this.wordServiceImpl = wordServiceImpl;
    }

    @GetMapping()
    List<WordResponse> getAll() {
        return wordServiceImpl.findAllByUser();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WordResponse create(@RequestBody CreateWordRequest request) {
        return wordServiceImpl.addWord(request);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WordResponse update(@RequestBody CreateWordRequest request) throws Exception {
        return wordServiceImpl.updateWord(request);

    }
}
