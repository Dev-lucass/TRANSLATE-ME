package com.example.TranslateMe.API.dto;

import lombok.Builder;

@Builder
public record ExerciseDTO(
        String text,
        String correctAnswer,
        String level
)
{}
