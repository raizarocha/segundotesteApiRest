package com.example.segundoteste.controller;

import com.example.segundoteste.entities.CandidatoEntity;
import com.example.segundoteste.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/hiring")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    @PostMapping("/start")
    public ResponseEntity<Integer> iniciarProcesso(@RequestBody CandidatoEntity nome) throws Exception {
        int newCandidato = candidatoService.iniciarProcesso(nome.getNome());
        return ResponseEntity.status(201).body(newCandidato);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Void> marcarEntrevista (@RequestBody CandidatoEntity codCandidato) throws Exception {
        candidatoService.marcarEntrevista(codCandidato.getId());
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/disqualify")
    public ResponseEntity<Void> desqualificarCandidato (@RequestBody CandidatoEntity codCandidato) throws Exception {
        candidatoService.desqualificarCandidato(codCandidato.getId());
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/approve")
    public ResponseEntity<Void> aprovarCandidato(@RequestBody CandidatoEntity codCandidato) throws Exception {
        candidatoService.aprovarCandidato(codCandidato.getId());
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/status/candidate/{codCandidato}")
    public ResponseEntity<String> verificarStatusCandidato (@PathVariable("codCandidato") int codCandidato) throws Exception {
        String statusVerify = candidatoService.verificarStatusCandidato(codCandidato);
        return ResponseEntity.status(200).body(statusVerify);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<String>> obterAprovados() {
        List<String> approved = candidatoService.obterAprovados();
        return ResponseEntity.status(200).body(approved);
    }
}
