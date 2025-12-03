package com.example.TranslateMe.API.service;

import com.example.TranslateMe.API.dto.ExerciseRequestDTO;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    public String ask(String answer) {
        ChatModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("gemma3:1b")
                .build();

        return model.chat(answer);
    }

    public String getPrompt(ExerciseRequestDTO requestDTO) {
        String prompt = """
                O usuário enviou uma tradução em português para um texto em inglês.
                
                Texto original em inglês:
                %s
                
                Tradução enviada pelo usuário:
                %s
                
                Sua função:
                - Avalie se a tradução está correta, parcialmente correta ou incorreta.
                - Dê dicas de melhoria (sem fornecer a resposta completa, a menos que ele tenha acertado, ai voce da parabens).
                - Aponte erros comuns que o usuário cometeu (se houver).
                - Use linguagem simples e motivadora.
                - Nunca forneça a tradução correta completa, a menos que ele tenha acertado, entao de parabens.
                """.formatted(
                requestDTO.originalText(),
                requestDTO.response()
        );
        return prompt;
    }
}
