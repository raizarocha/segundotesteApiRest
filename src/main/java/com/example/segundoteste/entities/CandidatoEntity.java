package com.example.segundoteste.entities;


public class CandidatoEntity {
    private Integer codCandidato;

    private String nome;

    private String status;


    public CandidatoEntity(Integer codCandidato, String nome, String status) {
        this.codCandidato = codCandidato;
        this.nome = nome;
        this.status = status;
    }
    

    public Integer getId() {
        return codCandidato;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
