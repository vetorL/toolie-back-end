package com.toolie.back_end.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    @Pattern(regexp = "[\\p{IsLatin}\\p{Digit}]*", message = "Nome deve possuir apenas caracteres alfanuméricos")
    private final String nome;

    @NotBlank(message = "Email é obrigatório")
    @Size(min = 6, max = 254, message = "Email deve ter entre 6 e 254 caracteres")
    @Column(unique = true, nullable = false)
    private final String email;

}
