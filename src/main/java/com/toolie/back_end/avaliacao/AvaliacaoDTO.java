package com.toolie.back_end.avaliacao;

import lombok.Data;

@Data
public class AvaliacaoDTO {
    private Long id;
    private Long avaliadorId;
    private Long ferramentaId;
    private Integer nota;
    private String comentario;
    private Date data_avaliacao;
}