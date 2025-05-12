package br.dormU.backend.controller.api;

import br.dormU.backend.dto.ImovelRequestDTO;
import br.dormU.backend.dto.ImovelResponseDTO;
import br.dormU.backend.service.ImovelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imoveis")
public class ImovelApiController {

    private final ImovelService imovelService;

    public ImovelApiController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @GetMapping
    public List<ImovelResponseDTO> listar() {
        return imovelService.listarTodosImoveis();
    }

    @GetMapping("/{id}")
    public ImovelResponseDTO detalhe(@PathVariable Long id) {
        return imovelService.buscarImovelPorId(id);
    }

    @PostMapping
    public ResponseEntity<ImovelResponseDTO> criar(@RequestBody @Valid ImovelRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(imovelService.salvarImovel(dto));
    }

    @PutMapping("/{id}")
    public ImovelResponseDTO atualizar(@PathVariable Long id,
                                       @RequestBody @Valid ImovelRequestDTO dto) {
        return imovelService.atualizarImovel(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        imovelService.excluirImovel(id);
        return ResponseEntity.noContent().build();
    }
}
