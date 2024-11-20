package com.toolie.back_end.aluguel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FerramentaEmAluguelDTO {
    private long id;
    private String nome;
    private String locatario;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFim;

    private String status;
}
