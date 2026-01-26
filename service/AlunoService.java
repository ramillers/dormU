package br.dormU.backend.service;

import br.dormU.backend.dto.AlunoRequestDTO;
import br.dormU.backend.dto.AlunoResponseDTO;
import br.dormU.backend.model.Aluno;
import br.dormU.backend.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // Listar todos os alunos e converter para DTOs
    public List<AlunoResponseDTO> listarTodosAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream()
                .map(aluno -> new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getEmail()))
                .collect(Collectors.toList());
    }

    // Buscar aluno por ID e retornar DTO
    public AlunoResponseDTO buscarAlunoPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getEmail()); // Mapeia para DTO
    }

    // Salvar um novo aluno
    public AlunoResponseDTO salvarAluno(AlunoRequestDTO alunoRequestDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequestDTO.getNome());
        aluno.setEmail(alunoRequestDTO.getEmail());
        aluno.setSenha(alunoRequestDTO.getSenha());
        aluno.setUniversidade(alunoRequestDTO.getUniversidade());
        aluno = alunoRepository.save(aluno);
        return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getEmail()); // Retorna o DTO de resposta
    }

    // Atualizar um aluno
    public AlunoResponseDTO atualizarAluno(Long id, AlunoRequestDTO alunoRequestDTO) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            Aluno aluno = alunoExistente.get();
            aluno.setNome(alunoRequestDTO.getNome());
            aluno.setEmail(alunoRequestDTO.getEmail());
            aluno.setSenha(alunoRequestDTO.getSenha());
            aluno.setUniversidade(alunoRequestDTO.getUniversidade());
            aluno = alunoRepository.save(aluno);
            return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
        }
        throw new RuntimeException("Aluno não encontrado para atualização");
    }

    // Excluir aluno
    public void excluirAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}
