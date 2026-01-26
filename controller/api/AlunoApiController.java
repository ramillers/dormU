package br.dormU.backend.controller.api;

import br.dormU.backend.dto.AlunoRequestDTO;
import br.dormU.backend.dto.AlunoResponseDTO;
import br.dormU.backend.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoApiController {

    private final AlunoService alunoService;

    public AlunoApiController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // LISTAR
    @GetMapping
    public List<AlunoResponseDTO> listar() {
        return alunoService.listarTodosAlunos();
    }

    // DETALHE
    @GetMapping("/{id}")
    public AlunoResponseDTO detalhe(@PathVariable Long id) {
        return alunoService.buscarAlunoPorId(id);
    }

    // CRIAR
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criar(@RequestBody @Valid AlunoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoService.salvarAluno(dto));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public AlunoResponseDTO atualizar(@PathVariable Long id,
                                      @RequestBody @Valid AlunoRequestDTO dto) {
        return alunoService.atualizarAluno(id, dto);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        alunoService.excluirAluno(id);
        return ResponseEntity.noContent().build();
    }
}
