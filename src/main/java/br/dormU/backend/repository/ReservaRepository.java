package br.dormU.backend.repository;

import br.dormU.backend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Aqui você pode adicionar métodos personalizados se necessário
    // Exemplo: List<Reserva> findByAlunoId(Long alunoId);
    List<Reserva> findByAlunoId(Long alunoId);  // Para buscar reservas de um aluno específico
    List<Reserva> findByImovelId(Long imovelId);  // Para buscar reservas de um imóvel específico
    List<Reserva> findByStatus(String status);  // Para buscar reservas por status (ex: "pendente", "aprovada")

}
