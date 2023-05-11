package com.example.Words.services;

import com.example.Words.dto.AddDictionaryRequest;
import com.example.Words.dto.AddDictionaryResponse;
import com.example.Words.dto.CreateWordRequest;
import com.example.Words.dto.WordResponse;
import com.example.Words.entities.Dictionary;
import com.example.Words.entities.User;
import com.example.Words.entities.Word;
import com.example.Words.exceptions.ElementNotFound;
import com.example.Words.exceptions.SuchElementAlreadyExist;
import com.example.Words.model.LearningStatus;
import com.example.Words.repositories.DictionaryRepository;
import com.example.Words.repositories.UserRepository;
import com.example.Words.repositories.WordRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class WordServiceImpl implements WordService {

    WordRepository wordRepository;
    UserRepository userRepository;
    DictionaryRepository dictionaryRepository;


    public WordServiceImpl(WordRepository wordRepository, UserRepository userRepository, DictionaryRepository dictionaryRepository) {

        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.dictionaryRepository=dictionaryRepository;

    }


    @Override
    @Transactional
    public List<WordResponse> findAll() {
        return wordRepository.findAll()
                .stream().map(this::buildWordResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public WordResponse findById(Integer id) {
        return wordRepository.findById(id).map(this::buildWordResponse)
                .orElseThrow(() -> new EntityNotFoundException("Word with id =" + id + " is not found!"));
    }

    @Override
    @Transactional
    public List<WordResponse> findByLearningStatus(LearningStatus status) {
        return wordRepository.findByLearningStatus(status).stream().map(this::buildWordResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public WordResponse addWord(CreateWordRequest request) {
        Dictionary dictionary = dictionaryRepository.findDictionaryByDictionaryName("New Dictionary#1");
        System.out.println(dictionary);
        //dictionary.setDictionaryName("Dict1");
        dictionary.setWords(wordRepository.findAll());


        request.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(request.getUser());
        Word word = buildWordRequest(request);
        List<Word> wordInBase = wordRepository.findByWordAndUser_Username(request.getWord(), request.getUser());
        if (wordInBase.size() < 1)
            return buildWordResponse(wordRepository.save(word));
        else throw new SuchElementAlreadyExist("Such element already exist");

    }

    @Override
    @Transactional
    public WordResponse updateWord(@NotNull CreateWordRequest request) throws Exception {
        Word word = wordRepository.findTopByWordAndUser_Username(request.getWord(), SecurityContextHolder.getContext().getAuthentication().getName());

        System.out.println("listWord=     ");
        System.out.println(word);

        if (word == null)
            throw new ElementNotFound("No such element");
        request.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
        wordUpdate(word, request);
        return buildWordResponse(wordRepository.saveAndFlush(word));
    }

    @Override
    public ResponseEntity<AddDictionaryResponse> addDictionary(AddDictionaryRequest request) {
        AddDictionaryResponse response = new AddDictionaryResponse();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));

    }

    @Override
    @Transactional
    public void delete(Integer id) {
        wordRepository.deleteById(id);

    }


    @NotNull
    private WordResponse buildWordResponse(@NotNull Word word) {
        return new WordResponse()
                .setId(word.getId())
                .setWord(word.getWord())
                .setUsername(word.getUser().getUsername())
                .setStatus(word.getLearningStatus())
                .setTranslate(word.getTranslate())
                .setContext(word.getContext())
                .setLastStudy(word.getLastStudy());

    }

    @NotNull
    private Word buildWordRequest(@NotNull CreateWordRequest request) {

        User user = userRepository.findByUsername(request.getUser());
        System.out.println(user.getUsername());

        Word word = new Word();


        word.setLastStudy(new Timestamp(System.currentTimeMillis()));
        word.setWord(request.getWord());
        word.setContext(request.getContext());
        word.setTranslate(request.getTranslate());
        word.setUser(user);
        word.setLearningStatus(request.getStatus());
        return word;

    }

    private void wordUpdate(@NotNull Word word, @NotNull CreateWordRequest request) {
        word.getUser().setUsername(request.getUser());
        ofNullable(request.getWord()).map(word::setWord);
        ofNullable(word.getUser()).map(word::setUser);
        ofNullable(request.getContext()).map(word::setContext);
        ofNullable(request.getStatus()).map(word::setLearningStatus);
        ofNullable(request.getTranslate()).map(word::setTranslate);


        User userRequest = userRepository.findByUsername(word.getUser().getUsername());

        if (userRequest != null) {
            ofNullable(userRequest.getUsername()).map(word.getUser()::setUsername);
            ofNullable(userRequest.getPassword()).map(word.getUser()::setPassword);
            ofNullable(userRequest.getRoles()).map(word.getUser()::setRoles);
            ofNullable(userRequest.getId()).map(word.getUser()::setId);

        }
    }

    public List<WordResponse> findAllByUser() {
        return wordRepository.findByUser_Username(SecurityContextHolder.getContext().getAuthentication().getName())
                .stream().map(this::buildWordResponse).collect(Collectors.toList());
    }




}















