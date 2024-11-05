package com.toolie.back_end.avaliacao;

import com.toolie.back_end.ferramenta.Ferramenta;
import com.toolie.back_end.usuario.Usuario;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "avaliador_id")
    private final Usuario avaliador;

    @ManyToOne
    @JoinColumn(name = "ferramenta_id")
    private final Ferramenta ferramenta;

    private final Integer nota;

    private final String comentario;

    @CreationTimestamp
    private Date data_avaliacao;

}
