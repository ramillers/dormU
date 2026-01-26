package br.dormU.backend.service;

import br.dormU.backend.dto.ImovelRequestDTO;
import br.dormU.backend.dto.ImovelResponseDTO;
import br.dormU.backend.model.Imovel;
import br.dormU.backend.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImovelService {

    private final ImovelRepository imovelRepository;

    @Autowired
    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    // Listar todos os imóveis e converter para DTOs
    public List<ImovelResponseDTO> listarTodosImoveis() {
        List<Imovel> imoveis = imovelRepository.findAll();
        return imoveis.stream()
                .map(imovel -> new ImovelResponseDTO(imovel.getId(), imovel.getTitulo(), imovel.getPreco(), imovel.getDescricao()))
                .collect(Collectors.toList());
    }

    // Buscar imóvel por ID e retornar DTO
    public ImovelResponseDTO buscarImovelPorId(Long id) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
        return new ImovelResponseDTO(imovel.getId(), imovel.getTitulo(), imovel.getPreco(), imovel.getDescricao());
    }

    // Salvar um imóvel
    public ImovelResponseDTO salvarImovel(ImovelRequestDTO imovelRequestDTO) {
        Imovel imovel = new Imovel();
        imovel.setTitulo(imovelRequestDTO.getTitulo());
        imovel.setDescricao(imovelRequestDTO.getDescricao());
        imovel.setPreco(imovelRequestDTO.getPreco());
        imovel.setEndereco(imovelRequestDTO.getEndereco());
        // Set alunoId de forma adequada, se necessário
        imovel = imovelRepository.save(imovel);

        return new ImovelResponseDTO(imovel.getId(), imovel.getTitulo(), imovel.getPreco(), imovel.getDescricao());
    }

    // Atualizar um imóvel
    public ImovelResponseDTO atualizarImovel(Long id, ImovelRequestDTO imovelRequestDTO) {
        Optional<Imovel> existingImovel = imovelRepository.findById(id);
        if (existingImovel.isPresent()) {
            Imovel imovel = existingImovel.get();
            imovel.setTitulo(imovelRequestDTO.getTitulo());
            imovel.setDescricao(imovelRequestDTO.getDescricao());
            imovel.setPreco(imovelRequestDTO.getPreco());
            imovel.setEndereco(imovelRequestDTO.getEndereco());
            imovel = imovelRepository.save(imovel);
            return new ImovelResponseDTO(imovel.getId(), imovel.getTitulo(), imovel.getPreco(), imovel.getDescricao());
        }
        throw new RuntimeException("Imóvel não encontrado para atualização");
    }

    // Excluir imóvel
    public void excluirImovel(Long id) {
        imovelRepository.deleteById(id);
    }
}
