package com.toolie.back_end.ferramenta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FerramentaRepository extends CrudRepository<Ferramenta, Long> {

    @Query("SELECT f FROM Ferramenta f WHERE LOWER(f.tipoFerramenta) LIKE LOWER(CONCAT('%', :tipoFerramenta, '%'))")
    List<Ferramenta> searchByTipoFerramenta(@Param("tipoFerramenta") String tipoFerramenta);

    List<Ferramenta> findByProprietarioId(Long id);

}
