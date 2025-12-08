package com.example.TranslateMe.API.service;

import com.example.TranslateMe.API.dto.ExerciseDTO;
import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    @Autowired
    private Client client;

    public String ask(ExerciseDTO exercise, String prompt) {
        String promptForGemini = getPrompt(exercise, prompt);
        return client.models.generateContent("gemini-2.5-flash", promptForGemini, null).text();
    }

    public String getPrompt(ExerciseDTO exercise, String userResponse) {
        return "Você é um assistente inteligente de correção de traduções de inglês para português (PT-BR).\n\n" +
                "O usuário fez a seguinte tradução:\n" +
                "Tradução correta: " + exercise.correctAnswer() + "\n" +
                "Tradução do usuário: " + userResponse + "\n\n" +
                "Sua tarefa é:\n" +
                "1) Avalie a tradução de forma natural e amigável.\n" +
                "2) Considere a tradução correta mesmo que o usuário tenha esquecido acentos, mas dê um aviso curto se faltar acentuação.\n" +
                "3) Indique apenas palavras ou expressões incorretas e dê dicas curtas para melhorar.\n" +
                "4) Não use caracteres especiais como asteriscos, setas ou Markdown.\n" +
                "5) Não forneça a tradução completa, a menos que o usuário peça explicitamente.\n" +
                "6) Responda em poucas frases, curta ou média, direta ao ponto.";
    }



}
