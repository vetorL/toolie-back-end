package com.toolie.back_end.ferramenta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ferramentas")
public class FerramentaController {

    private final FerramentaRepository ferramentaRepository;

    public FerramentaController(FerramentaRepository ferramentaRepository) {
        this.ferramentaRepository = ferramentaRepository;
    }

    @GetMapping
    public Iterable<Ferramenta> getFerramentas() {
        return ferramentaRepository.findAll();
    }

    @GetMapping("{id}")
    public Ferramenta getFerramenta(@PathVariable Long id) {
        return ferramentaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta não encontrada."));
    }

}
