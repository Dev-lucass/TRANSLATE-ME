package com.example.TranslateMe.API.mapper;

import com.example.TranslateMe.API.dto.ExerciseDTO;
import com.example.TranslateMe.API.model.Exercise;
import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public Exercise toExercise(ExerciseDTO dto) {
        return Exercise.builder()
                .text(dto.text())
                .level(ExerciseLevel.valueOf(dto.level()))
                .correctAnswer(dto.correctAnswer())
                .build();
    }

}
