package com.ttymonkey.webquizengine.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quizzes")
@NoArgsConstructor
@Getter
@Setter
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<OptionEntity> options;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "quiz_id", nullable = false)
    private Set<AnswerEntity> answers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public QuizEntity(
        String title,
        String text,
        List<OptionEntity> options,
        Set<AnswerEntity> answers
    ) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answers = answers;
    }
}

