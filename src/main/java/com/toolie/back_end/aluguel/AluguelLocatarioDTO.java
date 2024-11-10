package com.toolie.back_end.aluguel;

import lombok.Data;

import java.util.Date;

@Data
public class AluguelLocatarioDTO {
    private long id;
    private String tipoFerramenta;
    private Date dataInicio;
    private Date dataFim;
    private String statusAluguel;
    private String fotoFerramentaURL;

    // Static method to create AluguelLocatarioDTO from Aluguel entity
    public static AluguelLocatarioDTO fromAluguel(Aluguel aluguel) {
        AluguelLocatarioDTO dto = new AluguelLocatarioDTO();
        dto.setId(aluguel.getId());  // Assuming Aluguel has a getId() method
        dto.setTipoFerramenta(aluguel.getFerramenta().getTipoFerramenta());  // Assuming Aluguel has a getTipoFerramenta() method
        dto.setDataInicio(aluguel.getDataInicio());  // Assuming Aluguel has a getDataInicio() method
        dto.setDataFim(aluguel.getDataFim());  // Assuming Aluguel has a getDataFim() method
        dto.setStatusAluguel(aluguel.getStatusAluguel());  // Assuming Aluguel has a getStatusAluguel() method
        dto.setFotoFerramentaURL(aluguel.getFerramenta().getFotosURL());  // Assuming Aluguel has a getFotoFerramentaURL() method
        return dto;
    }
}
