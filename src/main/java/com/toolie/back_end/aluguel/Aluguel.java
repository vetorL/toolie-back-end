package com.toolie.back_end.aluguel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @ManyToOne
//    @JoinColumn(name = "locador_id")
//    private final Locador locador;

//    @ManyToOne
//    @JoinColumn(name = "locatario_id")
//    private final Locatario locatario;

    private final Date data_inicio;

    private final Date data_fim;

    private final String status_aluguel;

    private final String status_pagamento;

    private final String preco_final;

    private final String metodo_entrega;
    
}
