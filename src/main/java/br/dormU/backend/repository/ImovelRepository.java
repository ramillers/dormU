package br.dormU.backend.repository;

import br.dormU.backend.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    // Aqui você pode adicionar métodos personalizados se necessário
    // Exemplo: List<Imovel> findByPrecoBetween(Double minPrice, Double maxPrice);
    List<Imovel> findByPrecoBetween(Double minPrice, Double maxPrice);
    List<Imovel> findByAlunoId(Long alunoId);  // Para buscar imóveis de um aluno específico

}
