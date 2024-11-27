package com.toolie.back_end.ferramenta;

import com.toolie.back_end.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    private final Usuario proprietario;

    private final String tipoFerramenta;

    private final String estadoDeUso;

    private final String descricao;

    private final double precoAluguel;

    private Disponibilidade disponibilidade = Disponibilidade.DISPONIVEL;

    private final String localizacao;

    @ElementCollection
    private final List<String> fotosURL;

    private final String condicoesDeUso;

    private final String opcoesDeEntrega;

    private final Categoria categoria;

    private final double rating;

}
