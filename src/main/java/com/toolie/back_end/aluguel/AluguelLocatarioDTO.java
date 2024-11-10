package com.toolie.back_end.aluguel;

import lombok.Data;

import java.util.Date;

@Data
public class AluguelLocatarioDTO {
    private long id;
    private String tipo_ferramenta;
    private Date data_inicio;
    private Date data_fim;
    private String status_aluguel;
    private String foto_ferramenta_URL;

    // Static method to create AluguelLocatarioDTO from Aluguel entity
    public static AluguelLocatarioDTO fromAluguel(Aluguel aluguel) {
        AluguelLocatarioDTO dto = new AluguelLocatarioDTO();
        dto.setId(aluguel.getId());  // Assuming Aluguel has a getId() method
        dto.setTipo_ferramenta(aluguel.getFerramenta().getTipoFerramenta());  // Assuming Aluguel has a getTipoFerramenta() method
        dto.setData_inicio(aluguel.getDataInicio());  // Assuming Aluguel has a getDataInicio() method
        dto.setData_fim(aluguel.getDataFim());  // Assuming Aluguel has a getDataFim() method
        dto.setStatus_aluguel(aluguel.getStatusAluguel());  // Assuming Aluguel has a getStatusAluguel() method
        dto.setFoto_ferramenta_URL(aluguel.getFerramenta().getFotosURL());  // Assuming Aluguel has a getFotoFerramentaURL() method
        return dto;
    }
}
