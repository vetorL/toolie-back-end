package com.toolie.back_end.aluguel;

import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.usuario.Usuario;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "locador_id")
    private final Usuario locador;

    @ManyToOne
    @JoinColumn(name = "locatario_id")
    private final Usuario locatario;

    @ManyToOne
    @JoinColumn(name = "ferramenta_id")
    private final Ferramenta ferramenta;

    private final Date dataInicio;

    private final Date dataFim;

    private final String statusAluguel;

    private final String statusPagamento;

    private final String precoFinal;

    private final String metodoEntrega;
    
}
