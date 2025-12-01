package com.example.TranslateMe.API.service;

import com.example.TranslateMe.API.model.Exercise;
import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import com.example.TranslateMe.API.repository.ExerviceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerviceRepository repository;

    public Exercise save(Exercise exercise) {
        return repository.save(exercise);
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Exercise update(Long id, Exercise newExercise) {
        return repository.findById(id).map(existing -> {
                    existing.setText(newExercise.getText());
                    existing.setLevel(newExercise.getLevel());
                    return existing;
                })
                .orElseThrow();
    }

    public void delete(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public List<Exercise> findByLevel(ExerciseLevel level) {
        return repository.findByLevel(level);
    }

    public Exercise correctAnswer (String answer)  {
        return repository.findByCorrectAnswer(answer.toLowerCase());
    }

    public Optional<Exercise> findExerciseById(Long id) {
        return repository.findById(id);
    }
}
