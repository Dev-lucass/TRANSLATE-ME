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

import java.util.*;

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

    @GetMapping("/")
    public String showHomePage(Model model) {
        ExerciseLevel defaultLevel = ExerciseLevel.EASY;
        ResponseEntity<?> byLevel = exerciseController.findByLevel(defaultLevel);

        if (byLevel.getStatusCode().is2xxSuccessful() && byLevel.getBody() instanceof List<?> exercises && !exercises.isEmpty()) {
            ResponseDTO firstExercise = (ResponseDTO) ((List<?>) byLevel.getBody()).get(0);
            model.addAttribute("textoIngles", firstExercise.text());
        } else {
            model.addAttribute("textoIngles", "Nenhum exercício disponível.");
        }

        model.addAttribute("selectedLevel", defaultLevel.name());
        model.addAttribute("mensagens", Collections.emptyList());
        model.addAttribute("loading", false); // loader invisível inicialmente

        return "index"; // sua view Thymeleaf
    }


    @PostMapping("/chooseLevel")
    public String chooseLevel(@RequestParam("level") ExerciseLevel level, Model model) {

        ResponseEntity<?> byLevel = exerciseController.findByLevel(level);

        if (byLevel.getStatusCode().is2xxSuccessful() && byLevel.getBody() instanceof List<?> exercises && !exercises.isEmpty()) {

            List<ResponseDTO> list = (List<ResponseDTO>) exercises;

            ResponseDTO randomExercise = list.get(new Random().nextInt(list.size()));
            model.addAttribute("textoIngles", randomExercise.text());

        } else {
            model.addAttribute("textoIngles", "Nenhum exercício disponível.");
        }

        model.addAttribute("mensagens", Collections.emptyList());
        model.addAttribute("selectedLevel", level.name());

        return "index";
    }



    @PostMapping("/solve")
    public String solveExercise(
            @RequestParam("answer") String answer,
            @RequestParam("originalText") String originalText,
            Model model
    ) {

        model.addAttribute("loading", true);
        model.addAttribute("textoIngles", originalText);
        model.addAttribute("mensagens", List.of(criarMensagem(answer, "user")));
        model.addAttribute("selectedLevel", ExerciseLevel.EASY.name()); // ou pegar dinamicamente

        ExerciseRequestDTO dto = new ExerciseRequestDTO(answer, originalText);
        ResponseEntity<?> response = exerciseController.solveExercise(dto);

        List<Map<String, String>> mensagens = new ArrayList<>();
        mensagens.add(criarMensagem(answer, "user"));
        String respostaIA = response.getStatusCode().is2xxSuccessful() ? response.getBody().toString() : "Ocorreu um erro ao avaliar sua tradução.";
        mensagens.add(criarMensagem(respostaIA, "ai"));

        model.addAttribute("textoIngles", originalText);
        model.addAttribute("mensagens", mensagens);
        model.addAttribute("loading", false);

        return "index";
    }


    private Map<String, String> criarMensagem(String texto, String tipo) {
        return Map.of("texto", texto, "tipo", tipo);
    }


}
