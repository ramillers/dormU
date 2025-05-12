package br.dormU.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String email;
}
