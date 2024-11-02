package com.toolie.back_end.avaliacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final Integer nota;

    private final String comentario;

    @CreationTimestamp
    private Date data_avaliacao;

}
