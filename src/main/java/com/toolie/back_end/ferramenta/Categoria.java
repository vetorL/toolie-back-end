package com.toolie.back_end.ferramenta;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Categoria {
    CONSTRUCAO("Construção"),
    ENCANAMENTO("Encanamento"),
    JARDINAGEM("Jardinagem"),
    ELETRICA("Elétrica");

    private final String descricao;

    // Constructor
    Categoria(String descricao) {
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
