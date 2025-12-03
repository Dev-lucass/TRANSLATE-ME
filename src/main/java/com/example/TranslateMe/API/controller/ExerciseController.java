package com.example.TranslateMe.API.controller;

import com.example.TranslateMe.API.dto.ExerciseDTO;
import com.example.TranslateMe.API.dto.ExerciseRequestDTO;
import com.example.TranslateMe.API.dto.ResponseDTO;
import com.example.TranslateMe.API.exceptions.ExerciseIdNotFoundException;
import com.example.TranslateMe.API.mapper.ExerciseMapper;
import com.example.TranslateMe.API.model.Exercise;
import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import com.example.TranslateMe.API.service.ExerciseService;
import com.example.TranslateMe.API.service.GeminiService;
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
    private final GeminiService geminiService;
    private final ExerciseMapper mapper;

    // endpoint para o administrador
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
    public ResponseEntity<?> findByLevel(@PathVariable("level") ExerciseLevel level) {
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

    // endpoint para o administrador
    @PostMapping("/save")
    public ResponseEntity<?> saveExercise(@RequestBody ExerciseDTO dto) {
        try {
            log.info("POST");
            Exercise mapperExercise = mapper.toExercise(dto);
            Exercise saved = service.save(mapperExercise);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/solve")
    public ResponseEntity<?> solveExercise(@RequestBody ExerciseRequestDTO requestDTO) {
        try {
            log.info("POST/solve");

            ExerciseDTO exercise = service.findText(requestDTO.originalText());

            if (exercise == null) {
                return ResponseEntity.badRequest().body("Texto para traduzir não encontrado");
            }

            String aiResponse = geminiService.ask(exercise, requestDTO.response());

            if (aiResponse == null || aiResponse.isEmpty()) {
                return ResponseEntity.status(500).body("O modelo não retornou resposta. Verifique se o Ollama está rodando.");
            }

            return ResponseEntity.ok(aiResponse);

        } catch (Exception e) {
            log.error("Erro ao avaliar tradução", e);
            return ResponseEntity.status(500).body("Ocorreu um erro ao avaliar sua tradução: " + e.getMessage());
        }
    }


    // implementar no ViewController
    @PostMapping("/help/{id}")
    public ResponseEntity<?> helpExercise(@PathVariable("id") Long id) {
        try {
            log.info("POST/helpExercise");
            Exercise exercise = service.findExerciseById(id).orElseThrow(() -> new ExerciseIdNotFoundException("Exercise ID not found"));

            // return help here
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
