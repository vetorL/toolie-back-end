package com.toolie.back_end.aluguel;

import com.toolie.back_end.ferramenta.Disponibilidade;
import com.toolie.back_end.ferramenta.FerramentaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1")
public class AluguelController {

    private final AluguelRepository aluguelRepository;
    private final AluguelService aluguelService;
    private final FerramentaRepository ferramentaRepository;

    public AluguelController(AluguelRepository aluguelRepository, AluguelService aluguelService, FerramentaRepository ferramentaRepository) {
        this.aluguelRepository = aluguelRepository;
        this.aluguelService = aluguelService;
        this.ferramentaRepository = ferramentaRepository;
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

    @GetMapping("/usuarios/{id}/ferramentas-em-aluguel")
    public List<FerramentaEmAluguelDTO> getFerramentasEmAluguel(@PathVariable long id) {
        // Busca todos os aluguéis onde o usuário é locador e a ferramenta está alugada
        List<Aluguel> alugueis = aluguelRepository.findByLocadorIdAndFerramentaDisponibilidade(id, Disponibilidade.ALUGADA);

        // Mapeia os aluguéis para o DTO
        return alugueis.stream()
                .map(aluguel -> new FerramentaEmAluguelDTO(
                        aluguel.getId(),
                        aluguel.getFerramenta().getTipoFerramenta(),
                        aluguel.getLocatario().getNome(),
                        aluguel.getDataInicio(),
                        aluguel.getDataFim(),
                        aluguel.getStatusAluguel()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping("/alugueis")
    public AluguelResponse alugaFerramenta(@Valid @RequestBody AluguelRequest aluguel) {
        Aluguel entity = aluguelService.convertToEntity(aluguel);
        ferramentaRepository.setDisponibilidade(aluguel.getFerramentaId(), Disponibilidade.ALUGADA);
        return new AluguelResponse(aluguelRepository.save(entity));
    }

    @PostMapping("/alugueis/{id}/devolver")
    public AluguelResponse devolveFerramenta(@PathVariable Long id) {
        aluguelRepository.updateAluguelStatusAndDevolucao(id, "devolvido", new Date());

        Aluguel entity = aluguelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Aluguel not found with id: " + id));

        ferramentaRepository.setDisponibilidade(entity.getFerramenta().getId(), Disponibilidade.DISPONIVEL);

        return new AluguelResponse(entity);
    }

    // Private method to map Aluguel entity to AluguelLocatarioDTO
    private AluguelLocatarioDTO mapToDTO(Aluguel aluguel) {
        // Using the static method from AluguelLocatarioDTO to convert the Aluguel entity to DTO
        return AluguelLocatarioDTO.fromAluguel(aluguel);
    }
}
