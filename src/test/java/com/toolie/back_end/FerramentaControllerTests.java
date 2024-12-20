package com.toolie.back_end;

import com.toolie.back_end.ferramenta.*;
import com.toolie.back_end.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FerramentaController.class)
public class FerramentaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FerramentaRepository ferramentaRepository;

    private Ferramenta ferramenta1;
    private Ferramenta ferramenta2;
    private Usuario proprietario;

    private String baseURL = "/api/v1/ferramentas";

    @BeforeEach
    void setup() {
        proprietario = new Usuario("João", "joao@gmail.com", "1234567890", "Endereço Proprietario", "fotoURL");

        proprietario.setId(777);

        ferramenta1 = new Ferramenta(
                proprietario,
                "Martelo",
                EstadoDeUso.USADO,
                "Martelo de aço de 500g",
                20,
                "Centro",
                Collections.singletonList("fotosURL1"),
                CondicoesDeUso.BOM,
                "Retirada no local",
                Categoria.CONSTRUCAO,
                4.5
        );

        ferramenta2 = new Ferramenta(
                proprietario,
                "Chave de fenda",
                EstadoDeUso.NOVO,
                "Chave de fenda Philips",
                50,
                "Zona Sul",
                Collections.singletonList("fotosURL2"),
                CondicoesDeUso.BOM,
                "Entrega disponível",
                Categoria.CONSTRUCAO,
                4.5
        );
    }

    @Test
    public void testGetFerramentas() throws Exception {
        when(ferramentaRepository.findAll()).thenReturn(Arrays.asList(ferramenta1, ferramenta2));

        mockMvc.perform(get(baseURL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].tipoFerramenta").value("Martelo"))
                .andExpect(jsonPath("$[0].estadoDeUso").value("Usado"))
                .andExpect(jsonPath("$[0].descricao").value("Martelo de aço de 500g"))
                .andExpect(jsonPath("$[0].disponibilidade").value("Disponível"))
                .andExpect(jsonPath("$[0].localizacao").value("Centro"))
                .andExpect(jsonPath("$[0].fotosURL").value("fotosURL1"))
                .andExpect(jsonPath("$[0].condicoesDeUso").value("Bom"))
                .andExpect(jsonPath("$[0].opcoesDeEntrega").value("Retirada no local"))
                .andExpect(jsonPath("$[0].proprietarioId").value("777"))

                .andExpect(jsonPath("$[1].tipoFerramenta").value("Chave de fenda"))
                .andExpect(jsonPath("$[1].estadoDeUso").value("Novo"))
                .andExpect(jsonPath("$[1].descricao").value("Chave de fenda Philips"))
                .andExpect(jsonPath("$[1].disponibilidade").value("Disponível"))
                .andExpect(jsonPath("$[1].localizacao").value("Zona Sul"))
                .andExpect(jsonPath("$[1].fotosURL").value("fotosURL2"))
                .andExpect(jsonPath("$[1].condicoesDeUso").value("Bom"))
                .andExpect(jsonPath("$[1].opcoesDeEntrega").value("Entrega disponível"))
                .andExpect(jsonPath("$[0].proprietarioId").value("777"));
    }

    @Test
    public void testGetFerramentaByIdFound() throws Exception {
        // Mock the repository to return the ferramenta when the ID is 1
        when(ferramentaRepository.findById(1L)).thenReturn(Optional.of(ferramenta1));

        mockMvc.perform(get(baseURL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoFerramenta").value("Martelo"))
                .andExpect(jsonPath("$.estadoDeUso").value("Usado"))
                .andExpect(jsonPath("$.descricao").value("Martelo de aço de 500g"))
                .andExpect(jsonPath("$.disponibilidade").value("Disponível"))
                .andExpect(jsonPath("$.localizacao").value("Centro"))
                .andExpect(jsonPath("$.fotosURL").value("fotosURL1"))
                .andExpect(jsonPath("$.condicoesDeUso").value("Bom"))
                .andExpect(jsonPath("$.opcoesDeEntrega").value("Retirada no local"))
                .andExpect(jsonPath("$.proprietarioId").value("777"))
        ;
    }

    @Test
    public void testGetFerramentaByIdNotFound() throws Exception {
        // Mock the repository to return an empty Optional when the ID is not found
        when(ferramentaRepository.findById(9999L)).thenReturn(Optional.empty());

        mockMvc.perform(get(baseURL + "/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetFerramentasByTipoFerramentaWithQuery() throws Exception {
        // Given
        Ferramenta ferramenta1 = new Ferramenta(proprietario, "Drill", EstadoDeUso.NOVO, "Powerful drill", 20, "São Paulo", Collections.singletonList("foto1.jpg"), CondicoesDeUso.BOM, "Delivery available", Categoria.CONSTRUCAO, 4.5);
        Ferramenta ferramenta2 = new Ferramenta(proprietario, "Hammer", EstadoDeUso.USADO, "Sturdy hammer", 50, "Rio de Janeiro", Collections.singletonList("foto2.jpg"), CondicoesDeUso.BOM, "Pick up only", Categoria.CONSTRUCAO, 4.5);
        List<Ferramenta> ferramentas = Arrays.asList(ferramenta1);

        when(ferramentaRepository.searchByTipoFerramenta("Drill")).thenReturn(ferramentas);

        // When & Then
        mockMvc.perform(get(baseURL).param("q", "Drill"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoFerramenta").value("Drill"))
                .andExpect(jsonPath("$[0].descricao").value("Powerful drill"));
    }

    @Test
    void testGetFerramentasByTipoFerramentaWithoutQuery() throws Exception {
        // Given
        Ferramenta ferramenta1 = new Ferramenta(proprietario, "Drill", EstadoDeUso.NOVO, "Powerful drill", 20, "São Paulo", Collections.singletonList("foto1.jpg"), CondicoesDeUso.BOM, "Delivery available", Categoria.CONSTRUCAO, 4.5);
        Ferramenta ferramenta2 = new Ferramenta(proprietario, "Hammer", EstadoDeUso.USADO, "Sturdy hammer", 50, "Rio de Janeiro", Collections.singletonList("foto2.jpg"), CondicoesDeUso.BOM, "Pick up only", Categoria.CONSTRUCAO, 4.5);
        List<Ferramenta> ferramentas = Arrays.asList(ferramenta1, ferramenta2);

        when(ferramentaRepository.findAll()).thenReturn(ferramentas);

        // When & Then
        mockMvc.perform(get(baseURL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoFerramenta").value("Drill"))
                .andExpect(jsonPath("$[1].tipoFerramenta").value("Hammer"));
    }

    @Test
    void testGetFerramentasByUserIdWithResults() throws Exception {
        // Given
        List<Ferramenta> ferramentas = Arrays.asList(ferramenta1, ferramenta2);
        when(ferramentaRepository.findByProprietarioId(proprietario.getId())).thenReturn(ferramentas);

        // When & Then
        mockMvc.perform(get(baseURL).param("proprietarioId", String.valueOf(proprietario.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipoFerramenta").value("Martelo"))
                .andExpect(jsonPath("$[1].tipoFerramenta").value("Chave de fenda"))
                .andExpect(jsonPath("$[0].proprietarioId").value("777"))
                .andExpect(jsonPath("$[1].proprietarioId").value("777"));
    }

    @Test
    void testGetFerramentasByUserIdNoResults() throws Exception {
        // Given
        when(ferramentaRepository.findByProprietarioId(proprietario.getId())).thenReturn(List.of());

        // When & Then
        mockMvc.perform(get(baseURL).param("proprietarioId", String.valueOf(proprietario.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0)); // Expecting an empty array
    }

    @Test
    void testGetFerramentasByUserIdInvalidId() throws Exception {
        // Given an invalid user ID
        String invalidProprietarioId = "abc"; // non-numeric string

        // When & Then
        mockMvc.perform(get(baseURL).param("proprietarioId", invalidProprietarioId))
                .andExpect(status().isBadRequest()); // Expecting Bad Request due to parse error
    }
}
