package br.dormU.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String senha;
    @NotNull
    private String universidade;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Imovel> imoveis;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}
