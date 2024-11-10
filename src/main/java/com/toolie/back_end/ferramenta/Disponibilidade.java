package com.toolie.back_end.ferramenta;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Disponibilidade {
    DISPONIVEL("Disponível"),
    INDISPONIVEL("Indisponível"),
    ALUGADA("Alugada");

    private final String descricao;

    // Constructor
    Disponibilidade(String descricao) {
        this.descricao = descricao;
    }

    // Getter for the description
    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    // Optional: Override toString() to return the description instead of the enum name
    @Override
    public String toString() {
        return descricao;
    }
}