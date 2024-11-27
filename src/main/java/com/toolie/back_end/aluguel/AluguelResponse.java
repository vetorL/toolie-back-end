package com.toolie.back_end.aluguel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

import static java.time.ZoneId.systemDefault;

public class AluguelResponse {
    @JsonProperty("id")
    private long id;

    @JsonProperty("ferramenta_id")
    private long ferramentaId;

    @JsonProperty("locador_id")
    private long locadorId;

    @JsonProperty("locatario_id")
    private long locatarioId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data_inicio")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data_fim")
    private LocalDate dataFim;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data_devolucao")
    private LocalDate dataDevolucao;

    @JsonProperty("metodo_entrega")
    private String metodoEntrega;

    @JsonProperty("status")
    private String status;

    public AluguelResponse(Aluguel aluguel) {
        this.id = aluguel.getId();
        this.ferramentaId = aluguel.getFerramenta().getId();
        this.locadorId = aluguel.getLocador().getId();
        this.locatarioId = aluguel.getLocatario().getId();

        this.dataInicio = aluguel.getDataInicio().toInstant().atZone(systemDefault()).toLocalDate();
        this.dataFim = aluguel.getDataFim().toInstant().atZone(systemDefault()).toLocalDate();

        if (aluguel.getDataDevolucao() != null) {
            this.dataDevolucao = aluguel.getDataDevolucao().toInstant().atZone(systemDefault()).toLocalDate();
        }

        this.metodoEntrega = aluguel.getMetodoEntrega();
        this.status = aluguel.getStatusAluguel();
    }
}
