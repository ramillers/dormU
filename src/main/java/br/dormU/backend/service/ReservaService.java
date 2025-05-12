package br.dormU.backend.service;

import br.dormU.backend.dto.ReservaRequestDTO;
import br.dormU.backend.dto.ReservaResponseDTO;
import br.dormU.backend.exception.RecursoNaoEncontradoException;
import br.dormU.backend.model.Aluno;
import br.dormU.backend.model.Imovel;
import br.dormU.backend.model.Reserva;
import br.dormU.backend.model.StatusReserva;
import br.dormU.backend.repository.AlunoRepository;
import br.dormU.backend.repository.ImovelRepository;
import br.dormU.backend.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final AlunoRepository alunoRepository;
    private final ImovelRepository imovelRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, AlunoRepository alunoRepository, ImovelRepository imovelRepository) {
        this.reservaRepository = reservaRepository;
        this.alunoRepository = alunoRepository;
        this.imovelRepository = imovelRepository;
    }

    // Listar todas as reservas
    public List<ReservaResponseDTO> listarTodasReservas() {
        return reservaRepository.findAll()           // devolve List<Reserva>
                .stream()                             // vira Stream<Reserva>
                .map(reserva ->                      // abre map(
                        new ReservaResponseDTO(reserva)   // corpo do lambda
                )                                     // fecha map(...)
                .collect(Collectors.toList());        // agora .collect é do Stream
    }


    // Buscar uma reserva por ID
    public ReservaResponseDTO buscarReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        return new ReservaResponseDTO(reserva.getId(), reserva.getAluno().getId(), reserva.getImovel().getId(),  reserva.getStatus(), reserva.getDataInicio(), reserva.getDataFim());
    }

    // Buscar reservas de um aluno
    public List<Reserva> buscarReservasPorAluno(Long alunoId) {
        return reservaRepository.findByAlunoId(alunoId);
    }

    // Buscar reservas de um imóvel
    public List<Reserva> buscarReservasPorImovel(Long imovelId) {
        return reservaRepository.findByImovelId(imovelId);
    }

    // Criar nova reserva
    public ReservaResponseDTO salvarReserva(ReservaRequestDTO reservaRequestDTO) {
        // Buscar as entidades Aluno e Imóvel pelos IDs
        Aluno aluno = alunoRepository.findById(reservaRequestDTO.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Imovel imovel = imovelRepository.findById(reservaRequestDTO.getImovelId())
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        // Criar a entidade Reserva e associar o Aluno e Imóvel
        Reserva reserva = new Reserva();
        reserva.setDataInicio(reservaRequestDTO.getDataInicio());
        reserva.setDataFim(reservaRequestDTO.getDataFim());
        reserva.setStatus(reservaRequestDTO.getStatus());
        reserva.setAluno(aluno);  // Associando a entidade Aluno
        reserva.setImovel(imovel); // Associando a entidade Imóvel

        // Salvar a reserva no banco de dados
        reserva = reservaRepository.save(reserva);

        // Retornar o DTO de resposta
        return new ReservaResponseDTO(reserva.getId(), reserva.getAluno().getId(), reserva.getImovel().getId(),  reserva.getStatus(), reserva.getDataInicio(), reserva.getDataFim());
    }


    // Alterar status da reserva (ex: de "pendente" para "aprovada")
    public ReservaResponseDTO atualizarStatusReserva(Long id, String status) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Reserva não encontrada"));

        // converte String -> enum (gera IllegalArgumentException se vier errado)
        reserva.setStatus(StatusReserva.valueOf(status.toUpperCase()));

        reservaRepository.save(reserva);

        return new ReservaResponseDTO(reserva);     // ou fromEntity(reserva)
    }


    // Excluir reserva
    public void excluirReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}