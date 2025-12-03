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
                OBJETIVO
                Avaliar a tradução do usuário do inglês para o português (PT-BR), verificando se ela transmite corretamente o sentido, a estrutura, a gramática, a pontuação e a naturalidade.
                
                REGRAS PRINCIPAIS
                1) Se a tradução não corresponde ao original (cumprimento, frase fora de contexto, tema diferente), marque como INCORRETA e forneça a tradução correta.
                2) Se o sentido está correto, mas há pequenos erros de fluidez, léxico ou pontuação, marque como PARCIALMENTE CORRETA.
                3) Se a tradução está fiel e natural, marque como CORRETA. Sugira ajustes apenas se forem realmente úteis.
                
                FORMATO DE RESPOSTA
                Avaliação: CORRETA | PARCIALMENTE CORRETA | INCORRETA
                Forma correta: <tradução final em PT-BR; obrigatória se PARCIALMENTE CORRETA ou INCORRETA>
                Observações: <explicações curtas e claras sobre erros ou acertos, se necessário>
                Dica: <uma sugestão prática de como melhorar a tradução>
                Parabenização: <breve elogio se a tradução estiver correta>
                
                ESTILO
                - Use linguagem clara, acolhedora e direta.
                - Explique de forma simples por que algo está errado ou pode melhorar.
                - Dê exemplos curtos se necessário.
                - Não invente conteúdo além do texto original.
                
                EXEMPLOS
                Original: Despite the complexity of the task, she managed to complete it efficiently.
                Tradução do usuário: Olá! Como você está?
                Avaliação: INCORRETA
                Forma correta: Apesar da complexidade da tarefa, ela conseguiu concluí-la com eficiência.
                Observações: A resposta é um cumprimento, não traduz o conteúdo do original.
                Dica: Sempre identifique sujeito, ação e circunstâncias antes de traduzir.
                
                Original: I have been studying English for two years.
                Tradução do usuário: Eu tenho sido estudado em inglês por 2 anos.
                Avaliação: INCORRETA
                Forma correta: Eu estudo inglês há dois anos.
                Observações: Em português, usamos “há” para indicar duração e a voz ativa é mais natural.
                Dica: Mantenha a voz do sujeito e use “há” para tempo decorrido.
                
                Original: She quickly solved the problem.
                Tradução do usuário: Ela resolveu o problema rapidamente
                Avaliação: CORRETA
                Forma correta: Ela resolveu o problema rapidamente.
                Observações: Apenas adicionamos o ponto final para melhorar a apresentação.
                Parabenização: Ótima tradução — clara e natural.
                
                AVALIE AGORA
                
                Texto original em inglês:
                %s
                
                Tradução enviada pelo usuário:
                %s
                """.formatted(
                requestDTO.originalText(),
                requestDTO.response()
        );
        return prompt;
    }


}
