package br.dormU.backend.dto;

import java.time.LocalDate;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class ReservaDTO {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private Long alunoId;
    private Long imovelId;
}
