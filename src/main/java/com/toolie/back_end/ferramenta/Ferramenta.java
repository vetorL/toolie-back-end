package com.toolie.back_end.ferramenta;

import com.toolie.back_end.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Ferramenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private Usuario proprietario;

    private final String tipoFerramenta;

    private final String estadoDeUso;

    private final String descricao;

    private final String disponibilidade;

    private final String localizacao;

    private final String fotosURL;

    private final String condicoesDeUso;

    private final String opcoesDeEntrega;

}
