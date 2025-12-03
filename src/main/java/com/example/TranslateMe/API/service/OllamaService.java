package com.example.TranslateMe.API.service;

import com.example.TranslateMe.API.dto.ExerciseDTO;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    public String ask(String prompt) {
        ChatModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("mistral:7b")
                .build();

        return model.chat(prompt);
    }


    public String getPrompt(ExerciseDTO exercise, String userResponse) {
        return String.format("""
                        Você é um corretor de traduções de inglês para português (PT-BR).
                        
                        Compare a tradução do usuário com a tradução correta abaixo:
                        
                        Tradução correta: %s
                        Tradução do usuário: %s
                        
                        Instruções rigorosas:
                        
                        1) Se a tradução estiver completamente correta (ignorando pontuação e acentos), responda exatamente:
                           PARABÉNS! Sua tradução está correta.
                        
                        2) Se **uma ou mais palavras estiverem erradas**, liste **todas as palavras que estão diferentes ou traduzidas incorretamente** em relação à tradução correta, separadas por vírgula. **Não invente palavras**, **não repita palavras corretas**, **não adicione explicações**.
                        
                        3) Se quase todas as palavras estiverem erradas, responda exatamente:
                           A tradução está totalmente errada.
                        
                        ⚠️ Sempre siga estritamente estas instruções.
                        ⚠️ Nunca forneça a tradução correta completa, a menos que o usuário peça explicitamente.
                        
                        Analise cuidadosamente a tradução do usuário e siga apenas estas instruções.
                        """,
                exercise.correctAnswer(),
                userResponse
        );
    }


}
