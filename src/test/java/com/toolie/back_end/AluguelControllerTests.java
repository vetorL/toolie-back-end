package com.toolie.back_end;

import com.toolie.back_end.aluguel.Aluguel;
import com.toolie.back_end.aluguel.AluguelController;
import com.toolie.back_end.aluguel.AluguelRepository;
import com.toolie.back_end.ferramenta.Disponibilidade;
import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AluguelController.class)
public class AluguelControllerTests {

    // Use @MockBean to mock the AluguelRepository so it is injected into the controller
    @MockBean
    private AluguelRepository aluguelRepository;

    private MockMvc mockMvc;

    private Usuario locador;
    private Usuario locatario;
    private Ferramenta ferramenta1;

    @BeforeEach
    public void setUp() {
        locador = new Usuario(
                "Locador",
                "locador@example.com",
                "12345",
                "Rua 1",
                "http://example.com/documento.jpg"
        );
        locador.setId(1L);  // Explicitly set the ID for locador

        locatario = new Usuario(
                "Locatario",
                "locatario@example.com",
                "67890",
                "Rua 2",
                "http://example.com/documento2.jpg"
        );
        locatario.setId(2L);  // Explicitly set the ID for locatario

        ferramenta1 = new Ferramenta(
                locador,
                "Martelo",
                "Usado",
                "Martelo de aço de 500g",
                20,
                "Centro",
                Collections.singletonList("fotosURL1"),
                "Condições adequadas",
                "Retirada no local",
                "Construção"
        );

        // Initialize MockMvc to simulate HTTP requests
        mockMvc = MockMvcBuilders.standaloneSetup(new AluguelController(aluguelRepository)).build();
    }

    @Test
    public void testFerramentasAlugadas() throws Exception {
        // Setup sample data for the test
        Date dataInicio = new Date();
        Date dataFim = new Date(dataInicio.getTime() + 86400000L);  // 1 day later

        Aluguel aluguel1 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, "ativo", "pago", "20.0", "retirada no local");
        Aluguel aluguel2 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, "ativo", "pago", "30.0", "envio por motoboy");

        // Mock the repository response
        when(aluguelRepository.findByLocatarioId(2L)).thenReturn(Arrays.asList(aluguel1, aluguel2));

        // Perform the request and verify the response
        mockMvc.perform(get("/api/v1/usuarios/{userId}/ferramentas-alugadas", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Check the status code
                .andExpect(jsonPath("$[0].statusAluguel").value("ativo"))  // Check the first aluguel
                .andExpect(jsonPath("$[1].statusAluguel").value("ativo"));  // Check the second aluguel

        // Verify that the repository was called once
        verify(aluguelRepository, times(1)).findByLocatarioId(2L);
    }

    @Test
    public void testFerramentasAlugadas_NoAluguel() throws Exception {
        // Mock the repository response to return an empty list
        when(aluguelRepository.findByLocatarioId(2L)).thenReturn(Arrays.asList());

        // Perform the request and verify the response
        mockMvc.perform(get("/api/v1/usuarios/{userId}/ferramentas-alugadas", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Status should be OK
                .andExpect(content().json("[]"));  // No alugueis should be returned (empty list)

        // Verify that the repository was called once
        verify(aluguelRepository, times(1)).findByLocatarioId(2L);
    }

    @Test
    public void testFerramentasEmAluguel() throws Exception {
        // Setup sample data for the test
        Date dataInicio = new Date();
        Date dataFim = new Date(dataInicio.getTime() + 86400000L);  // 1 day later

        Aluguel aluguel1 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, "ativo", "pago", "20.0", "retirada no local");
        Aluguel aluguel2 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, "ativo", "pago", "30.0", "envio por motoboy");

        // Mock the repository response for rented tools that are in use
        when(aluguelRepository.findByLocadorIdAndFerramentaDisponibilidade(1L, Disponibilidade.ALUGADA))
                .thenReturn(Arrays.asList(aluguel1, aluguel2));

        // Perform the request and verify the response
        mockMvc.perform(get("/api/v1/usuarios/{userId}/ferramentas-em-aluguel", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Status should be OK
                .andExpect(jsonPath("$[0].status").value("ativo"))  // Check the first aluguel
                .andExpect(jsonPath("$[1].status").value("ativo"));  // Check the second aluguel

        // Verify that the repository was called once
        verify(aluguelRepository, times(1)).findByLocadorIdAndFerramentaDisponibilidade(1L, Disponibilidade.ALUGADA);
    }

}
