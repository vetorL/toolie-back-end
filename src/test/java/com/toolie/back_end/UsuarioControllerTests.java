package com.toolie.back_end;

import com.toolie.back_end.usuario.Usuario;
import com.toolie.back_end.usuario.UsuarioController;
import com.toolie.back_end.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private Usuario usuario1;
    private Usuario usuario2;

    private String baseURL = "/api/v1/usuarios";

    @BeforeEach
    void setup() {
        usuario1 = new Usuario("Jorge", "jorge@gmail.com", "1234567890", "Endereço 1", "url1");
        usuario2 = new Usuario("Maria", "maria@gmail.com", "2345678901", "Endereço 2", "url2");
    }

    @Test
    public void testGetAllUsuarios() throws Exception {
        Mockito.when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get(baseURL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].nome").value("Jorge"))
                .andExpect(jsonPath("$[0].email").value("jorge@gmail.com"))
                .andExpect(jsonPath("$[1].nome").value("Maria"))
                .andExpect(jsonPath("$[1].email").value("maria@gmail.com"));
    }

    @Test
    public void testGetUsuarioById_Found() throws Exception {
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario1));

        mockMvc.perform(get(baseURL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nome").value("Jorge"))
                .andExpect(jsonPath("$.email").value("jorge@gmail.com"));
    }

    @Test
    public void testGetUsuarioById_NotFound() throws Exception {
        Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get(baseURL + "/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
