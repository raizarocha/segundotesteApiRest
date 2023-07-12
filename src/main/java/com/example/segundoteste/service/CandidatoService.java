package com.example.segundoteste.service;

import com.example.segundoteste.entities.CandidatoEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CandidatoService {
    public Random id = new Random();
    public List<CandidatoEntity> candidatos = new ArrayList<>();

    public int iniciarProcesso(String nome) throws Exception {
        if (nome == null) {
            throw new Exception("Nome inválido");
        }
        if (!nome.isEmpty()) {
            boolean candidatoExiste = buscaCandidatoPorNome(nome);
            if (candidatoExiste) {
                throw  new Exception("Candidato já participa do processo");
            }
        }

        Integer codCandidato = id.nextInt(1000);
        String status = "Recebido";
        CandidatoEntity candidato = new CandidatoEntity(codCandidato, nome, status);
        candidatos.add(candidato);
        return candidato.getId();
    }

    public boolean buscaCandidatoPorNome(String nome) {
        return candidatos.stream().anyMatch(candidato -> candidato.getNome().equals(nome));
    }

    public CandidatoEntity buscaCandidatoPorId(int id) {
        for (CandidatoEntity candidato : candidatos) {
            if (candidato.getId() == id) {
                return candidato;
            }
        }
        return null;
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        CandidatoEntity candidato = buscaCandidatoPorId(codCandidato);
        if (candidato == null || !Objects.equals(candidato.getStatus(), "Recebido")) {
            throw new Exception("Candidato não encontrado");
        } else {
            candidato.setStatus("Qualificado");
        }
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        CandidatoEntity candidato = buscaCandidatoPorId(codCandidato);
        if (candidato == null) {
            throw new Exception("Candidato não encontrado");
        } else {
            candidatos.remove(candidato);
        }
    }

    public String verificarStatusCandidato(int codCandidato) throws Exception {
        CandidatoEntity candidato = buscaCandidatoPorId(codCandidato);
        if (candidato == null) {
            throw new Exception("Candidato não encontrado");
        } else {
            return candidato.getStatus();
        }
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        CandidatoEntity candidato = buscaCandidatoPorId(codCandidato);
        if (candidato == null || !Objects.equals(candidato.getStatus(), "Qualificado")) {
            throw new Exception("Candidato não encontrado");
        } else {
            candidato.setStatus("Aprovado");
        }
    }

    public List<String> obterAprovados(){
        List<String> aprovados = new ArrayList<>();
        for (CandidatoEntity candidato : candidatos) {
            if (candidato.getStatus().equals("Aprovado")) {
                aprovados.add(candidato.getNome());
            }
        }

        return aprovados;
    }
}
