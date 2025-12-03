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

                    // ================== EASY ==================
                    Exercise.builder()
                            .text("Hello! How are you?")
                            .level(ExerciseLevel.EASY)
                            .correctAnswer("Olá! Como você está?")
                            .build(),

                    Exercise.builder()
                            .text("I like to play soccer with my friends.")
                            .level(ExerciseLevel.EASY)
                            .correctAnswer("Eu gosto de jogar futebol com meus amigos.")
                            .build(),

                    Exercise.builder()
                            .text("She lives in a small house near the park.")
                            .level(ExerciseLevel.EASY)
                            .correctAnswer("Ela mora em uma casa pequena perto do parque.")
                            .build(),

                    Exercise.builder()
                            .text("We need to buy food for the week.")
                            .level(ExerciseLevel.EASY)
                            .correctAnswer("Nós precisamos comprar comida para a semana.")
                            .build(),

                    Exercise.builder()
                            .text("My brother works in a hospital.")
                            .level(ExerciseLevel.EASY)
                            .correctAnswer("Meu irmão trabalha em um hospital.")
                            .build(),


                    // ================== MEDIUM ==================
                    Exercise.builder()
                            .text("I have been studying English for two years.")
                            .level(ExerciseLevel.MEDIUM)
                            .correctAnswer("Eu estudo inglês há dois anos.")
                            .build(),

                    Exercise.builder()
                            .text("She usually goes to the gym early in the morning.")
                            .level(ExerciseLevel.MEDIUM)
                            .correctAnswer("Ela geralmente vai à academia cedo pela manhã.")
                            .build(),

                    Exercise.builder()
                            .text("They decided to move because the city was too noisy.")
                            .level(ExerciseLevel.MEDIUM)
                            .correctAnswer("Eles decidiram se mudar porque a cidade era muito barulhenta.")
                            .build(),

                    Exercise.builder()
                            .text("Even though it was raining, we continued our walk.")
                            .level(ExerciseLevel.MEDIUM)
                            .correctAnswer("Mesmo que estivesse chovendo, nós continuamos nossa caminhada.")
                            .build(),

                    Exercise.builder()
                            .text("He didn’t accept the offer because it wasn’t what he expected.")
                            .level(ExerciseLevel.MEDIUM)
                            .correctAnswer("Ele não aceitou a oferta porque não era o que ele esperava.")
                            .build(),


                    // ================== HARD ==================
                    Exercise.builder()
                            .text("Despite the complexity of the task, she managed to complete it efficiently.")
                            .level(ExerciseLevel.HARD)
                            .correctAnswer("Apesar da complexidade da tarefa, ela conseguiu completá-la de forma eficiente.")
                            .build(),

                    Exercise.builder()
                            .text("The research findings suggest that our assumptions were fundamentally flawed.")
                            .level(ExerciseLevel.HARD)
                            .correctAnswer("Os resultados da pesquisa sugerem que nossas suposições estavam fundamentalmente equivocadas.")
                            .build(),

                    Exercise.builder()
                            .text("If he had known about the consequences, he would have acted differently.")
                            .level(ExerciseLevel.HARD)
                            .correctAnswer("Se ele soubesse das consequências, ele teria agido de forma diferente.")
                            .build(),

                    Exercise.builder()
                            .text("Her ability to analyze complex problems makes her an invaluable member of the team.")
                            .level(ExerciseLevel.HARD)
                            .correctAnswer("A capacidade dela de analisar problemas complexos a torna um membro inestimável da equipe.")
                            .build(),

                    Exercise.builder()
                            .text("Although the system was designed to prevent errors, several unexpected issues occurred.")
                            .level(ExerciseLevel.HARD)
                            .correctAnswer("Embora o sistema tenha sido projetado para evitar erros, vários problemas inesperados ocorreram.")
                            .build()
            ));
        }
    }
}
