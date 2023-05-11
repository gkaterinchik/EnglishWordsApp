package com.example.Words.entities;

import com.example.Words.model.LearningStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Accessors(chain = true)
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="word")
    private String word;
    @Column(name="translate")
    private String translate;
    @Column(name="context")
    private String context;
    @Column(name="learning_status")
    @Enumerated(EnumType.ORDINAL)
    private LearningStatus learningStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(name="last_study")
    private Timestamp lastStudy;


    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translate='" + translate + '\'' +
                ", context='" + context + '\'' +
                ", learningStatus='" + learningStatus + '\'' +
                ", user='" +  user.getUsername()+ '\'' +
                ", lastStudy='" + lastStudy.getTime()+ '\'' +
                '}';
    }


}
