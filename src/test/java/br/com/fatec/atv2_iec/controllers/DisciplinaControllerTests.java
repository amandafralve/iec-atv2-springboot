package br.com.fatec.atv2_iec.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DisciplinaControllerTests {
    @Autowired
	private MockMvc mockMvc;

	@Test
	void deveRetornarListaDeDisciplinas() throws Exception {
		// Lista de strings é convertida pelo Spring para JSON automaticamente
		String jsonEsperado = "[\"Laboratório de Desenvolvimento WEB\",\"Estatística\",\"Programação para Dispositivos Móveis\"]";

		mockMvc.perform(get("/disciplinas"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

			.andExpect(content().string(jsonEsperado));
	}

    @Test
    void deveRetornarDisciplinaId() throws Exception{
        mockMvc.perform(get("/disciplinas/1"))
            .andExpect((status().isOk()))
            .andExpect(content().string("Laboratório de Desenvolvimento WEB"));
    }

    @Test
    void deveAdicionarNovaDisciplina() throws Exception {
    Map<String, String> novaDisciplina = Map.of("codigo", "4", "nome", "Banco de Dados Relacional");
    String json = new ObjectMapper().writeValueAsString(novaDisciplina);

    mockMvc.perform(post("/disciplinas")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isOk())
        .andExpect(content().string("Disciplina adicionada"));
}

}
