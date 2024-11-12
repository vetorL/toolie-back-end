package com.toolie.back_end.ferramenta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api/v1/ferramentas")
public class FerramentaController {

    private final FerramentaRepository ferramentaRepository;

    public FerramentaController(FerramentaRepository ferramentaRepository) {
        this.ferramentaRepository = ferramentaRepository;
    }

    @GetMapping("{id}")
    public Ferramenta getFerramenta(@PathVariable Long id) {
        return ferramentaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta não encontrada."));
    }

    @GetMapping
    public List<Ferramenta> getFerramentasByTipoFerramenta(@RequestParam(required = false) String q) {
        if (q != null) {
            return ferramentaRepository.searchByTipoFerramenta(q);
        }
        return (List<Ferramenta>) ferramentaRepository.findAll();
    }

    @GetMapping(params = "proprietarioId")
    public List<Ferramenta> getFerramentasByUserId(@RequestParam String proprietarioId) {
        try {
            Long id = parseLong(proprietarioId);
            return ferramentaRepository.findByProprietarioId(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "proprietarioId deve ser um número");
        }
    }

}
