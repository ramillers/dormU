package br.dormU.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;

    private String descricao;
    @NotNull
    private String endereco;
    @NotNull
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}
