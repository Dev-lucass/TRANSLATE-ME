package com.example.TranslateMe.API.config;

import com.example.TranslateMe.API.model.Exercise;
import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import com.example.TranslateMe.API.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ExerciseRepository repository;

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {

            repository.saveAll(List.of(

                    Exercise.builder()
                            .text("Hello! How are you?")
                            .level(ExerciseLevel.EASY)
                            .correctAnswer("Olá! Como você está?")
                            .build(),

                    Exercise.builder()
                            .text("I have been studying English for two years.")
                            .level(ExerciseLevel.MEDIUM)
                            .correctAnswer("Eu estudo inglês há dois anos.")
                            .build(),

                    Exercise.builder()
                            .text("Despite the complexity of the task, she managed to complete it efficiently.")
                            .level(ExerciseLevel.HARD)
                            .correctAnswer("Apesar da complexidade da tarefa, ela conseguiu completá-la de forma eficiente.")
                            .build()
            ));

        }
    }
}
