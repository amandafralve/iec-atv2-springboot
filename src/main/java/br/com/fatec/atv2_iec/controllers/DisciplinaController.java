package br.com.fatec.atv2_iec.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/disciplinas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Disciplina", description = "Consulta de disciplinas Fatec Registro")
public class DisciplinaController {

    private final HashMap<Integer, String> disciplinas =
            new HashMap<>(Map.of(
                    1, "Laboratório de Desenvolvimento WEB",
                    2, "Estatística",
                    3, "Programação para Dispositivos Móveis"
            ));

    @GetMapping
    @Operation(summary = "Lista as disciplinas Fatec", description = "Endpoint para consulta das disciplinas")
    public ResponseEntity<Map<Integer, String>> getDisciplinas() {
        // devolve JSON do próprio mapa
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDisciplinasId(@PathVariable Integer id) {
        String disciplina = disciplinas.get(id);
        if (disciplina == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Disciplina não encontrada", "id", id));
        }
        // embrulha em objeto para sair como JSON (e não text/plain)
        return ResponseEntity.ok(Map.of("id", id, "nome", disciplina));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> adicionarDisciplina(
            @org.springframework.web.bind.annotation.RequestBody Map<String, String> requestDisciplina) {

        Integer cod = Integer.parseInt(requestDisciplina.get("codigo"));
        String nome = requestDisciplina.get("nome");

        disciplinas.put(cod, nome);

        // retorna 201 com o recurso criado em JSON
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", cod, "nome", nome, "message", "Disciplina adicionada"));
    }
}
