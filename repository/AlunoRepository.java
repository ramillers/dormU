package br.dormU.backend.repository;

import br.dormU.backend.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Aqui você pode adicionar métodos personalizados se necessário
    // Exemplo: Optional<Aluno> findByEmail(String email);
    Optional<Aluno> findByEmail(String email);

}
