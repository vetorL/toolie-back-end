package com.toolie.back_end.ferramenta;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoDeUso {
    NOVO("Novo"),
    USADO("Usado"),
    SEMINOVO("Seminovo");

    private final String descricao;

    // Constructor
    EstadoDeUso(String descricao) {
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
