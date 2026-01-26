package br.dormU.backend.controller.api;

import br.dormU.backend.dto.ReservaRequestDTO;
import br.dormU.backend.dto.ReservaResponseDTO;
import br.dormU.backend.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaApiController {

    private final ReservaService reservaService;

    public ReservaApiController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<ReservaResponseDTO> listar() {
        return reservaService.listarTodasReservas();
    }

    @GetMapping("/{id}")
    public ReservaResponseDTO detalhe(@PathVariable Long id) {
        return reservaService.buscarReservaPorId(id);
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(@RequestBody @Valid ReservaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.salvarReserva(dto));
    }

    // Atualizar status (pendente/aprovada/rejeitada)
    @PutMapping("/{id}/status")
    public ResponseEntity<ReservaResponseDTO> alterarStatus(@PathVariable Long id,
                                                            @RequestParam String status) {
        ReservaResponseDTO dto = reservaService.atualizarStatusReserva(id, status);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        reservaService.excluirReserva(id);
        return ResponseEntity.noContent().build();
    }
}
