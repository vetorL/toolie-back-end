package com.toolie.back_end.aluguel;

import com.toolie.back_end.ferramenta.Disponibilidade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AluguelRepository extends CrudRepository<Aluguel, Long> {

    List<Aluguel> findByLocatarioId(long locatarioId);

    List<Aluguel> findByLocadorIdAndFerramentaDisponibilidade(long locadorId, Disponibilidade disponibilidade);

}
