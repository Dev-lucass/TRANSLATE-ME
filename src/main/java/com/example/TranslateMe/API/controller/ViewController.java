package com.example.TranslateMe.API.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ExerciseController controller;

    @GetMapping("/")
    public String showAllExercises (Model model) {
        ResponseEntity<?> exercises = controller.findAllExercises();
        model.addAttribute("exercises",exercises);
        return "index";
    }

}
