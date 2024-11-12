package com.toolie.back_end.aluguel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AluguelLocatarioDTO {
    private long id;
    private String tipoFerramenta;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFim;

    private String statusAluguel;
    private List<String> fotosFerramentaURL;

    // Static method to create AluguelLocatarioDTO from Aluguel entity
    public static AluguelLocatarioDTO fromAluguel(Aluguel aluguel) {
        AluguelLocatarioDTO dto = new AluguelLocatarioDTO();
        dto.setId(aluguel.getId());  // Assuming Aluguel has a getId() method
        dto.setTipoFerramenta(aluguel.getFerramenta().getTipoFerramenta());  // Assuming Aluguel has a getTipoFerramenta() method
        dto.setDataInicio(aluguel.getDataInicio());  // Assuming Aluguel has a getDataInicio() method
        dto.setDataFim(aluguel.getDataFim());  // Assuming Aluguel has a getDataFim() method
        dto.setStatusAluguel(aluguel.getStatusAluguel());  // Assuming Aluguel has a getStatusAluguel() method
        dto.setFotosFerramentaURL(aluguel.getFerramenta().getFotosURL());  // Assuming Aluguel has a getFotoFerramentaURL() method
        return dto;
    }
}
