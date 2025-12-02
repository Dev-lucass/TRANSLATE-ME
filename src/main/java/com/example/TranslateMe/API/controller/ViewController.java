package com.example.TranslateMe.API.controller;

import com.example.TranslateMe.API.dto.ExerciseRequestDTO;
import com.example.TranslateMe.API.dto.ResponseDTO;
import com.example.TranslateMe.API.model.enums.ExerciseLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ExerciseController exerciseController;

    // implementar botao para traduzir o texto quando o usuario desistir
    @GetMapping("/translateText")
    public String showTranslationPage(@RequestParam("answer") ExerciseRequestDTO answer, Model model) {

        ResponseEntity<?> response = exerciseController.solveExercise(answer);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() instanceof List<?> exercisesList && !exercisesList.isEmpty()) {
            model.addAttribute("textoIngles", response.getBody());

        } else {
            model.addAttribute("textoIngles", "Texto sem traducao no momento.");
        }

        return "traducao";
    }

    // implementar quando integrar OpenAI
    @PostMapping("/sendAnswer")
    public String sendAnswer(@RequestParam("message") ExerciseRequestDTO mensagem, Model model) {

        ResponseEntity<?> response = exerciseController.solveExercise(mensagem);

        List<Object> mensagens = Collections.emptyList();

        if (response.getStatusCode().is2xxSuccessful()) {

            Object body = response.getBody();

            mensagens = List.of(
                    Collections.singletonMap("tipo", "user"),
                    Collections.singletonMap("tipo", "ai")
            );

            model.addAttribute("textoIngles", body);

        } else {
            model.addAttribute("textoIngles", "Resposta incorreta ou erro.");
        }

        model.addAttribute("mensagens", mensagens);
        return "traducao";
    }

    @PostMapping("/chooseLevel")
    public String levelEasy(@RequestParam("level") ExerciseLevel level, Model model) {

        ResponseEntity<?> byLevel = exerciseController.findByLevel(level);

        if (byLevel.getStatusCode().is2xxSuccessful() && byLevel.getBody() instanceof List<?> exercises && !exercises.isEmpty()) {

            ResponseDTO firstExercise = (ResponseDTO) exercises.getFirst();
            model.addAttribute("textoIngles", firstExercise.text());

        } else {
            model.addAttribute("textoIngles", "Nenhum exercício disponível.");
        }

        model.addAttribute("mensagens", Collections.emptyList());
        return "index";
    }


}
