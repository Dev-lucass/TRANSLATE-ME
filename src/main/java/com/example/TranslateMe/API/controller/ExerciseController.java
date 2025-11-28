package com.example.TranslateMe.API.controller;

import com.example.TranslateMe.API.dto.ExerciseDTO;
import com.example.TranslateMe.API.dto.ResponseDTO;
import com.example.TranslateMe.API.model.Exercise;
import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import com.example.TranslateMe.API.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ExerciseController {

    private final ExerciseService service;

    @GetMapping("/exercise")
    public ResponseEntity<?> findAllExercises() {
        try {
            log.info("GET");
            List<ResponseDTO> all = service.findAll()
                    .stream()
                    .map(exercise -> new ResponseDTO(exercise.getText(), exercise.getLevel()))
                    .toList();

            if (all.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{level}")
    public ResponseEntity<?> findAllExercises(@PathVariable("level") ExerciseLevel level) {
        try {
            log.info("GET/level");

            List<ResponseDTO> all = service.findByLevel(level)
                    .stream()
                    .map(exercise -> new ResponseDTO(exercise.getText(), exercise.getLevel()))
                    .toList();

            if (all.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveExercise(@RequestBody Exercise exercise) {
        try {
            log.info("POST");
            Exercise saved = service.save(exercise);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/solve")
    public ResponseEntity<?> solve(@RequestBody String answer) {
        try {
            log.info("POST/solve");

            Exercise correctAnswer = service.correctAnswer(answer);

            if (correctAnswer == null) {
                return ResponseEntity.badRequest().body("Incorret Answer, try again ...");
            }

            ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                    .text(correctAnswer.getText())
                    .correctAnswer(correctAnswer.getCorrectAnswer().toLowerCase())
                    .level(correctAnswer.getLevel().toString())
                    .build();

            return ResponseEntity.ok(exerciseDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
