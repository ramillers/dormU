package br.dormU.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ImovelRequestDTO {

    @NotBlank(message = "Título não pode ser vazio")
    private String titulo;

    private String descricao;

    @NotNull(message = "Preço não pode ser nulo")
    private Double preco;

    @NotBlank(message = "Endereço não pode ser vazio")
    private String endereco;

    private Long alunoId;  // ID do aluno que está criando o anúncio

    // Getters and Setters
}
