package com.toolie.back_end.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        try {
            Avaliacao avaliacaoCriada = avaliacaoService.createAvaliacao(avaliacao);
            return new ResponseEntity<>(avaliacaoCriada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{ferramentaId}")
    public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoesByFerramentaId(@PathVariable Long ferramentaId) {
        List<AvaliacaoDTO> avaliacoes = avaliacaoService.getAvaliacoesByFerramentaId(ferramentaId);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<AvaliacaoDTO>> getAvaliacoesByFerramentaId(@RequestParam Long ferramentaId, Pageable pageable) {
        Page<AvaliacaoDTO> avaliacoes = avaliacaoService.getAvaliacoesByFerramentaId(ferramentaId, pageable);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }
}