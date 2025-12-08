package com.example.TranslateMe.API.model;

import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "text")
    private String text;

    @Column(nullable = false, name = "level")
    @Enumerated(EnumType.STRING)
    private ExerciseLevel level;

    @Column(nullable = false, name = "correctAnswer")
    private String correctAnswer;

}
