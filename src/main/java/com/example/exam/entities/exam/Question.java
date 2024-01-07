package com.example.exam.entities.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long quesId;
    @Column(length = 5000)
    private  String content;
    private  String image;
    private  String option1;
    private  String option2;
    private  String option3;
    private  String option4;

    private  String answer;
    @Transient
    private  String givenAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

}
