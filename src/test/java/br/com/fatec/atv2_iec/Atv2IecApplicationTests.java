package br.com.fatec.atv2_iec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class Atv2IecApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveRetornarListaDeDisciplinas() throws Exception {
		// Lista de strings é convertida pelo Spring para JSON automaticamente
		String jsonEsperado = "[\"Laboratório de Desenvolvimento WEB\",\"Estatística\",\"Programação para Dispositivos Móveis\"]";

		mockMvc.perform(get("/disciplinas"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(jsonEsperado));
	}

}
