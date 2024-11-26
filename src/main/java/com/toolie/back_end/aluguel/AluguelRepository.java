package com.toolie.back_end.aluguel;

import com.toolie.back_end.ferramenta.Disponibilidade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface AluguelRepository extends CrudRepository<Aluguel, Long> {

    List<Aluguel> findByLocatarioId(long locatarioId);

    List<Aluguel> findByLocadorIdAndFerramentaDisponibilidade(long locadorId, Disponibilidade disponibilidade);

    @Modifying
    @Transactional
    @Query("UPDATE Aluguel a SET a.statusAluguel = :statusAluguel, a.dataDevolucao = :dataDevolucao WHERE a.id = :id")
    void updateAluguelStatusAndDevolucao(@Param("id") Long id,
                                         @Param("statusAluguel") String statusAluguel,
                                         @Param("dataDevolucao") Date dataDevolucao);

}
