package com.toolie.back_end.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public Avaliacao createAvaliacao(Avaliacao avaliacao) {
        validateAvaliacao(avaliacao);
        return avaliacaoRepository.save(avaliacao);
    }

    private void validateAvaliacao(Avaliacao avaliacao) {
        if (avaliacao.getAvaliador() == null) {
            throw new IllegalArgumentException("O avaliador não pode ser nulo.");
        }
        if (avaliacao.getFerramenta() == null) {
            throw new IllegalArgumentException("A ferramenta não pode ser nula.");
        }
        if (avaliacao.getNota() == null) {
            throw new IllegalArgumentException("A nota não pode ser nula.");
        }
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 5.");
        }
        if (avaliacao.getComentario() == null) {
            throw new IllegalArgumentException("O comentário não pode ser nulo.");
        }
    }

    public List<AvaliacaoDTO> getAvaliacoesByFerramentaId(Long ferramentaId) {
        try {
            List<Avaliacao> avaliacoes = avaliacaoRepository.findByFerramentaId(ferramentaId);
            return avaliacoes.stream()
                    .map(this::converterParaDTO)
                    .collect(Collectors.toList());
        } catch (EmptyResultDataAccessException e) {
            return List.of(); // retorna lista vazia em vez de exceção
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar avaliações", e);
        }
    }

    private AvaliacaoDTO converterParaDTO(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());
        dto.setAvaliadorId(avaliacao.getAvaliador().getId());
        dto.setFerramentaId(avaliacao.getFerramenta().getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setData_avaliacao(avaliacao.getData_avaliacao());
        return dto;
    }

    public Page<AvaliacaoDTO> getAvaliacoesByFerramentaId(Long ferramentaId, Pageable pageable) {
        try{
            Page<Avaliacao> avaliacoes = avaliacaoRepository.findByFerramentaId(ferramentaId, pageable);
            return avaliacoes.map(this::converterParaDTO);
        } catch (EmptyResultDataAccessException e) {
            return Page.empty();
        } catch (Exception e){
            throw new RuntimeException("Erro ao buscar avaliações", e);
        }
    }
}