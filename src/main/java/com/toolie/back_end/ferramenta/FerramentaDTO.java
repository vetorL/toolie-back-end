package com.toolie.back_end.ferramenta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FerramentaDTO {

    private long id;

    private long proprietarioId;

    private String tipoFerramenta;

    private String estadoDeUso;

    private String descricao;

    private double precoAluguel;

    private Disponibilidade disponibilidade;

    private String localizacao;

    private List<String> fotosURL;

    private String condicoesDeUso;

    private String opcoesDeEntrega;

    private Categoria categoria;

    private double rating;

    public static FerramentaDTO fromFerramenta(Ferramenta ferramenta) {
        return new FerramentaDTO(
                ferramenta.getId(),
                ferramenta.getProprietario().getId(),
                ferramenta.getTipoFerramenta(),
                ferramenta.getEstadoDeUso(),
                ferramenta.getDescricao(),
                ferramenta.getPrecoAluguel(),
                ferramenta.getDisponibilidade(),
                ferramenta.getLocalizacao(),
                ferramenta.getFotosURL(),
                ferramenta.getCondicoesDeUso(),
                ferramenta.getOpcoesDeEntrega(),
                ferramenta.getCategoria(),
                ferramenta.getRating()
        );
    }

}
