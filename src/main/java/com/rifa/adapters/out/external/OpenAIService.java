package com.rifa.adapters.out.external;

import com.rifa.adapters.out.external.dto.OpenAIRequest;
import com.rifa.adapters.out.external.dto.OpenAIResponse;
import com.rifa.domain.model.Premio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAIService {

    private final WebClient webClient;
    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    public OpenAIService(@Value("${openai.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(OPENAI_URL)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public List<Premio> generarPremios(String tema, int cantidad) {
        try {
            String prompt = construirPrompt(tema, cantidad);

            OpenAIRequest request = new OpenAIRequest(prompt);

            OpenAIResponse response = webClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(OpenAIResponse.class)
                    .block();

            if (response != null && !response.getChoices().isEmpty()) {
                String contenido = response.getChoices().get(0).getMessage().getContent();
                return parsearPremios(contenido, cantidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private String construirPrompt(String tema, int cantidad) {
        return "Genera una lista de " + cantidad + " premios creativos para una rifa de tema '" + tema + "'. "
             + "Cada premio debe ser breve sin frases adicionales a la descripción del premio. Incluye un emoji para cada uno al final. "
             + "Los premios deben estar ordenados de mejor premio a peor premio";
    }

    private List<Premio> parsearPremios(String texto, int cantidadEsperada) {
        List<Premio> premios = new ArrayList<>();
        String[] lineas = texto.split("\n");

        for (String linea : lineas) {
            if (premios.size() >= cantidadEsperada) break;

            // Quita numeración si existe
            String premioTexto = linea.replaceFirst("^\\d+\\.\\s*", "").trim(); 
            premios.add(new Premio(premioTexto, null));
        }
        return premios;
    }

}
