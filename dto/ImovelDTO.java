package br.dormU.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ImovelDTO {
    private Long id;
    private String titulo;
    private Double preco;
    private String descricao;
}