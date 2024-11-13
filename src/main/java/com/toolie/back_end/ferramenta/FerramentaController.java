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
    public FerramentaDTO getFerramenta(@PathVariable Long id) {
        return FerramentaDTO.fromFerramenta(ferramentaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta não encontrada.")));
    }

    @GetMapping
    public List<FerramentaDTO> getFerramentasByTipoFerramenta(@RequestParam(required = false) String q) {
        List<Ferramenta> ferramentas;
        if (q != null) {
            ferramentas = ferramentaRepository.searchByTipoFerramenta(q);
        } else {
            ferramentas = (List<Ferramenta>) ferramentaRepository.findAll();
        }
        return ferramentas.stream().map(this::mapToDTO).toList();
    }

    @GetMapping(params = "proprietarioId")
    public List<FerramentaDTO> getFerramentasByUserId(@RequestParam String proprietarioId) {
        try {
            Long id = parseLong(proprietarioId);
            return ferramentaRepository.findByProprietarioId(id)
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "proprietarioId deve ser um número");
        }
    }

    // Private method to map Ferramenta entity to FerramentaDTO
    private FerramentaDTO mapToDTO(Ferramenta ferramenta) {
        // Using the static method from FerramentaDTO to convert the Ferramenta entity to DTO
        return FerramentaDTO.fromFerramenta(ferramenta);
    }

}
