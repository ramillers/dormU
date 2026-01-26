package br.dormU.backend.dto;

import java.time.LocalDate;

import br.dormU.backend.model.Reserva;
import br.dormU.backend.model.StatusReserva;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponseDTO {

    private Long id;
    private Long alunoId;
    private Long imovelId;
    @Enumerated(EnumType.STRING)
    private StatusReserva status;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Construtor que converte a entidade -> DTO
    public ReservaResponseDTO(Reserva reserva) {
        this.id        = reserva.getId();
        this.alunoId   = reserva.getAluno().getId();
        this.imovelId  = reserva.getImovel().getId();
        this.status    = reserva.getStatus();
        this.dataInicio= reserva.getDataInicio();
        this.dataFim   = reserva.getDataFim();
    }
}

