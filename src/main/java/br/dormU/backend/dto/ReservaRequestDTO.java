package br.dormU.backend.dto;

import br.dormU.backend.model.StatusReserva;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ReservaRequestDTO {

    @NotNull(message = "Data de início não pode ser nula")
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim não pode ser nula")
    private LocalDate dataFim;

    @NotNull(message = "Status não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @NotNull(message = "Aluno ID não pode ser nulo")
    private Long alunoId;

    @NotNull(message = "Imóvel ID não pode ser nulo")
    private Long imovelId;

    // Getters and Setters
}
