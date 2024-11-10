package com.toolie.back_end.aluguel;

import com.toolie.back_end.ferramenta.Disponibilidade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AluguelController {

    private final AluguelRepository aluguelRepository;

    public AluguelController(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    @GetMapping("/usuarios/{userId}/ferramentas-alugadas")
    public List<AluguelLocatarioDTO> ferramentasAlugadas(@PathVariable long userId) {
        // Fetching the Aluguel entities from the repository
        List<Aluguel> alugueis = aluguelRepository.findByLocatarioId(userId);

        // Mapping the Aluguel entities to AluguelLocatarioDTO
        return alugueis.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/usuarios/{userId}/ferramentas-em-aluguel")
    public List<Aluguel> ferramentasEmAluguel(@PathVariable long userId) {
        return aluguelRepository.findByLocadorIdAndFerramentaDisponibilidade(userId, Disponibilidade.ALUGADA);
    }

    // Private method to map Aluguel entity to AluguelLocatarioDTO
    private AluguelLocatarioDTO mapToDTO(Aluguel aluguel) {
        // Using the static method from AluguelLocatarioDTO to convert the Aluguel entity to DTO
        return AluguelLocatarioDTO.fromAluguel(aluguel);
    }
}
