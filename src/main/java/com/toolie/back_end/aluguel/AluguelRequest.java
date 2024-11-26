package com.toolie.back_end.aluguel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AluguelRequest {
    @JsonProperty("ferramenta_id")
    @NotNull
    private Long ferramentaId;

    @JsonProperty("locador_id")
    @NotNull
    private Long locadorId;

    @JsonProperty("locatario_id")
    @NotNull
    private Long locatarioId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data_inicio")
    @NotNull
    private LocalDate dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data_fim")
    @NotNull
    private LocalDate dataFim;

    @JsonProperty("metodo_entrega")
    private String metodoEntrega;
}
