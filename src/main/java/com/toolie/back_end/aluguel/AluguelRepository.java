package com.toolie.back_end.aluguel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AluguelRepository extends CrudRepository<Aluguel, Long> {

    List<Aluguel> findByLocatarioId(long locatarioId);

}
