package com.example.TranslateMe.API.repository;

import com.example.TranslateMe.API.model.Exercise;
import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByLevel(ExerciseLevel level);

    Exercise findByCorrectAnswer(String correctAnswer);

    Exercise findByText(String text);
}
