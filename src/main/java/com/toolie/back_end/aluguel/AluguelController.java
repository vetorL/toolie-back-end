package com.toolie.back_end.aluguel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AluguelController {

    private final AluguelRepository aluguelRepository;

    public AluguelController(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    @GetMapping("/usuarios/{userId}/ferramentas-alugadas")
    public List<Aluguel> ferramentasAlugadas(@PathVariable long userId) {
        return aluguelRepository.findByLocatarioId(userId);
    }

}
