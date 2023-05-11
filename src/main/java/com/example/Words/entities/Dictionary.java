package com.example.Words.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;
@Entity
@Data
@Table(name = "Dictionary")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="dictionary_name")
    private String dictionaryName;

    @ManyToMany
    @JoinTable(name = "dictionary_words",
            joinColumns = @JoinColumn(name = "dictionary_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private Collection<Word> words;

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", dictionaryName='" + dictionaryName + '\'' +
                ", words=" + words +
                '}';
    }
}
