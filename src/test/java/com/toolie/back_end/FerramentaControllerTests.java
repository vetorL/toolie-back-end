package com.toolie.back_end;

import com.toolie.back_end.usuario.Usuario;
import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.ferramenta.FerramentaController;
import com.toolie.back_end.ferramenta.FerramentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FerramentaController.class)
public class FerramentaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FerramentaRepository ferramentaRepository;

    private Ferramenta ferramenta1;
    private Ferramenta ferramenta2;
    private Usuario proprietario;

    @BeforeEach
    void setup() {
        proprietario = new Usuario("João", "joao@gmail.com", "1234567890", "Endereço Proprietario", "fotoURL");

        ferramenta1 = new Ferramenta(
                proprietario,
                "Martelo",
                "Usado",
                "Martelo de aço de 500g",
                "Disponível",
                "Centro",
                "fotosURL1",
                "Condições adequadas",
                "Retirada no local"
        );

        ferramenta2 = new Ferramenta(
                proprietario,
                "Chave de fenda",
                "Novo",
                "Chave de fenda Philips",
                "Indisponível",
                "Zona Sul",
                "fotosURL2",
                "Condições novas",
                "Entrega disponível"
        );
    }

    @Test
    public void testGetFerramentas() throws Exception {
        Mockito.when(ferramentaRepository.findAll()).thenReturn(Arrays.asList(ferramenta1, ferramenta2));

        mockMvc.perform(get("/ferramentas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].tipoFerramenta").value("Martelo"))
                .andExpect(jsonPath("$[0].estadoDeUso").value("Usado"))
                .andExpect(jsonPath("$[0].descricao").value("Martelo de aço de 500g"))
                .andExpect(jsonPath("$[0].disponibilidade").value("Disponível"))
                .andExpect(jsonPath("$[0].localizacao").value("Centro"))
                .andExpect(jsonPath("$[0].fotosURL").value("fotosURL1"))
                .andExpect(jsonPath("$[0].condicoesDeUso").value("Condições adequadas"))
                .andExpect(jsonPath("$[0].opcoesDeEntrega").value("Retirada no local"))
                .andExpect(jsonPath("$[0].proprietario.nome").value("João"))
                .andExpect(jsonPath("$[0].proprietario.email").value("joao@gmail.com"))

                .andExpect(jsonPath("$[1].tipoFerramenta").value("Chave de fenda"))
                .andExpect(jsonPath("$[1].estadoDeUso").value("Novo"))
                .andExpect(jsonPath("$[1].descricao").value("Chave de fenda Philips"))
                .andExpect(jsonPath("$[1].disponibilidade").value("Indisponível"))
                .andExpect(jsonPath("$[1].localizacao").value("Zona Sul"))
                .andExpect(jsonPath("$[1].fotosURL").value("fotosURL2"))
                .andExpect(jsonPath("$[1].condicoesDeUso").value("Condições novas"))
                .andExpect(jsonPath("$[1].opcoesDeEntrega").value("Entrega disponível"))
                .andExpect(jsonPath("$[1].proprietario.nome").value("João"))
                .andExpect(jsonPath("$[1].proprietario.email").value("joao@gmail.com"));
    }
}
