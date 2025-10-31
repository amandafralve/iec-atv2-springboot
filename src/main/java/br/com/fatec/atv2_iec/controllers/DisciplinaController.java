package br.com.fatec.atv2_iec.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
@Tag(name = "Disciplina", description = "Consulta de disciplinas Fatec Registro")

public class DisciplinaController {

    private final HashMap<Integer, String> disciplinas = new HashMap<>(Map.of(1, "Laboratório de Desenvolvimento WEB", 2, "Estatística", 3, "Programação para Dispositivos Móveis"));
    

    @GetMapping
    @Operation(summary = "Lista as disciplinas Fatec", description = "Endpoint para consulta das disciplinas")
    public ResponseEntity<String> getDisciplinas(){
        // Retornando 
        return ResponseEntity.ok(disciplinas.toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getDisciplinasId(@PathVariable Integer id){
        
        String disciplina = disciplinas.get(id);
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<String> adicionarDisciplina(@RequestBody Map<String, String> requestDisciplina){
        Integer cod = Integer.parseInt(requestDisciplina.get("codigo"));
        String nome = requestDisciplina.get("nome");
        disciplinas.put(cod, nome);
        return ResponseEntity.ok("Disciplina adicionada");
    }
}
