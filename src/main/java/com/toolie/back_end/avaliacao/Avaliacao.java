package com.toolie.back_end.avaliacao;

import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "avaliador_id")
    private final Usuario avaliador;

    @ManyToOne
    @JoinColumn(name = "ferramenta_id")
    private final Ferramenta ferramenta;

    private final Integer nota;

    private final String comentario;

    @CreationTimestamp
    private final Date data_avaliacao;
}