package com.toolie.back_end.avaliacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.ferramenta.id = :ferramentaId")
    List<Avaliacao> findByFerramentaId(@Param("ferramentaId") Long ferramentaId);

    @Query("SELECT a FROM Avaliacao a WHERE a.ferramenta.id = :ferramentaId")
    Page<Avaliacao> findByFerramentaId(@Param("ferramentaId") Long ferramentaId, Pageable pageable);
}