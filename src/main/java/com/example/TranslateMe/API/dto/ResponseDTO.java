package com.example.TranslateMe.API.dto;

import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import lombok.Builder;

@Builder
public record ResponseDTO(
        String text,
        ExerciseLevel level
) {}
