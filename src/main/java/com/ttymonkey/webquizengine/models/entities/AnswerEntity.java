package com.ttymonkey.webquizengine.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@NoArgsConstructor
@Getter
@Setter
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "value")
    private Integer value;

    public AnswerEntity(int value) {
        this.value = value;
    }
}
