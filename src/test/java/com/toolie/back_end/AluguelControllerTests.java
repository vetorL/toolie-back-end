package com.toolie.back_end;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.toolie.back_end.aluguel.*;
import com.toolie.back_end.ferramenta.Disponibilidade;
import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.ferramenta.FerramentaRepository;
import com.toolie.back_end.usuario.Usuario;
import com.toolie.back_end.usuario.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static java.time.ZoneId.systemDefault;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AluguelController.class)
@ContextConfiguration(classes = {AluguelControllerTests.Config.class})
public class AluguelControllerTests {

    // Use @MockBean to mock the AluguelRepository so it is injected into the controller
    @MockBean
    private AluguelRepository aluguelRepository;

    // Use @MockBean to mock the FerramentaRepository so it is injected into the controller
    @MockBean
    private FerramentaRepository ferramentaRepository;

    // Use @MockBean to mock the UsuarioRepository so it is injected into the service
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AluguelService aluguelService;

    static class Config {
        @Bean
        @Primary
        public AluguelService aluguelService(UsuarioRepository usuarioRepository, FerramentaRepository ferramentaRepository) {
            return new AluguelService(usuarioRepository, ferramentaRepository);
        }
    }

    private MockMvc mockMvc;

    private Usuario locador;
    private Usuario locatario;
    private Ferramenta ferramenta1;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
                "Construção",
                4.5
        );

        // Initialize MockMvc to simulate HTTP requests
        mockMvc = MockMvcBuilders.standaloneSetup(new AluguelController(aluguelRepository, aluguelService, ferramentaRepository)).build();

        objectMapper.registerModule(new JavaTimeModule()); // This will handle Java 8 date/time types
    }

    @Test
    public void testFerramentasAlugadas() throws Exception {
        // Setup sample data for the test
        Date dataInicio = new Date();
        Date dataFim = new Date(dataInicio.getTime() + 86400000L);  // 1 day later

        Aluguel aluguel1 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, null, "ativo", "pago", "20.0", "retirada no local");
        Aluguel aluguel2 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, null, "ativo", "pago", "30.0", "envio por motoboy");

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

        Aluguel aluguel1 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, null, "ativo", "pago", "20.0", "retirada no local");
        Aluguel aluguel2 = new Aluguel(locador, locatario, ferramenta1, dataInicio, dataFim, null, "ativo", "pago", "30.0", "envio por motoboy");

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

    @Test
    public void testAlugarFerramenta() throws Exception {
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = dataInicio.plusDays(2);

        Aluguel expectedEntity = new Aluguel(locador, locatario, ferramenta1, getFromLocalDate(dataInicio), getFromLocalDate(dataFim), null, "pendente", "pendente", "40.0", "retirada no local");

        when(usuarioRepository.findById(locador.getId())).thenReturn(Optional.of(locador));
        when(usuarioRepository.findById(locatario.getId())).thenReturn(Optional.of(locatario));
        when(ferramentaRepository.findById(ferramenta1.getId())).thenReturn(Optional.of(ferramenta1));

        when(aluguelRepository.save(expectedEntity)).thenReturn(expectedEntity);

        AluguelRequest request = new AluguelRequest(ferramenta1.getId(), locador.getId(), locatario.getId(), dataInicio, dataFim, "retirada no local");
        String requestBody = objectMapper.writeValueAsString(request);

        // Perform the request and verify the response
        MvcResult result = mockMvc.perform(post("/api/v1/alugueis")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = objectMapper.writeValueAsString(new AluguelResponse(expectedEntity));
        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(expectedResponse, response);

        verify(ferramentaRepository, times(1)).setDisponibilidade(ferramenta1.getId(), Disponibilidade.ALUGADA);
    }

    @Test
    public void testDevolverFerramenta() throws Exception {
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = dataInicio.plusDays(2);
        LocalDate dataDevolucao = LocalDate.now();

        Aluguel aluguel = new Aluguel(locador, locatario, ferramenta1, getFromLocalDate(dataInicio), getFromLocalDate(dataFim), getFromLocalDate(dataDevolucao), "pendente", "pendente", "40.0", "retirada no local");
        aluguel.setId(1);

        when(aluguelRepository.findById(aluguel.getId())).thenReturn(Optional.of(aluguel));

        // Perform the request and verify the response
        MvcResult result = mockMvc.perform(post("/api/v1/alugueis/" + aluguel.getId() + "/devolver"))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = objectMapper.writeValueAsString(new AluguelResponse(aluguel));
        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(expectedResponse, response);

        verify(aluguelRepository, times(1)).updateAluguelStatusAndDevolucao(eq(aluguel.getId()), eq("devolvido"), any());
        verify(ferramentaRepository, times(1)).setDisponibilidade(ferramenta1.getId(), Disponibilidade.DISPONIVEL);

    }

    private Date getFromLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(systemDefault()).toInstant());
    }
}
