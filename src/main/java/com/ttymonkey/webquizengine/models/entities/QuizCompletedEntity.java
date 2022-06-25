package com.ttymonkey.webquizengine.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "completions")
@NoArgsConstructor
@Getter
@Setter
public class QuizCompletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuizEntity quiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "completed_at")
    @CreationTimestamp
    private Date completedAt;

    public QuizCompletedEntity(
        QuizEntity quiz,
        UserEntity user
    ) {
        this.quiz = quiz;
        this.user = user;
    }
}
