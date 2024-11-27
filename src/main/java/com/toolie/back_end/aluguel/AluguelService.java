package com.toolie.back_end.aluguel;

import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.ferramenta.FerramentaRepository;
import com.toolie.back_end.usuario.Usuario;
import com.toolie.back_end.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;

import static java.time.ZoneId.systemDefault;
import static java.time.temporal.ChronoUnit.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AluguelService {

    private final UsuarioRepository usuarioRepository;
    private final FerramentaRepository ferramentaRepository;

    public AluguelService(UsuarioRepository usuarioRepository, FerramentaRepository ferramentaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ferramentaRepository = ferramentaRepository;
    }

    public Aluguel convertToEntity(AluguelRequest request) {
        // Fetch related entities
        Usuario locador = usuarioRepository.findById(request.getLocadorId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Locador not found with id: " + request.getLocadorId()));
        Usuario locatario = usuarioRepository.findById(request.getLocatarioId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Locatario not found with id: " + request.getLocatarioId()));
        Ferramenta ferramenta = ferramentaRepository.findById(request.getFerramentaId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Ferramenta not found with id: " + request.getFerramentaId()));

        // Build Aluguel entity
        return new Aluguel(
                locador,
                locatario,
                ferramenta,
                Date.from(request.getDataInicio().atStartOfDay(systemDefault()).toInstant()),
                Date.from(request.getDataFim().atStartOfDay(systemDefault()).toInstant()),
                null,
                "pendente", // default status for aluguel
                "pendente",  // default payment status
                calculatePrice(ferramenta, request.getDataInicio(), request.getDataFim()), // Example price logic
                request.getMetodoEntrega()
        );
    }

    private String calculatePrice(Ferramenta ferramenta, LocalDate dataInicio, LocalDate dataFim) {
        return String.valueOf(ferramenta.getPrecoAluguel() * DAYS.between(dataInicio, dataFim));
    }
}
