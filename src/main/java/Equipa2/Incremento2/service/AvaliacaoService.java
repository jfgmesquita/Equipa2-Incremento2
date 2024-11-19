package Equipa2.Incremento2.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Avaliacao;
import Equipa2.Incremento2.repository.AvaliacaoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

/**
 * Serviço para operações relacionadas com a entidade Avaliacao.
 */
@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    /**
     * Encontra todas as avaliações.
     *
     * @return uma lista de todas as avaliações
     */
    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    /**
     * Encontra uma avaliação pelo ID.
     *
     * @param id o ID da avaliação
     * @return a avaliação com o ID fornecido
     * @throws IllegalArgumentException se o ID for nulo
     * @throws ResourceNotFoundException se a avaliação não for encontrada
     */
    public Avaliacao findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com o ID: " + id));
    }

    /**
     * Guarda uma avaliação.
     *
     * @param avaliacao a avaliação a ser guardada
     * @return a avaliação guardada
     * @throws IllegalArgumentException se a avaliação for nula
     */
    public Avaliacao save(Avaliacao avaliacao) {
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não pode ser nula.");
        }

        return avaliacaoRepository.save(avaliacao);
    }

    /**
     * Atualiza uma avaliação.
     *
     * @param id o ID da avaliação a ser atualizada
     * @param avaliacaoDetails os detalhes da avaliação a serem atualizados
     * @return a avaliação atualizada
     * @throws IllegalArgumentException se o ID ou os detalhes da avaliação forem nulos
     * @throws ResourceNotFoundException se a avaliação não for encontrada
     */
    public Avaliacao update(UUID id, Avaliacao avaliacaoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (avaliacaoDetails == null) {
            throw new IllegalArgumentException("Detalhes da avaliação não podem ser nulos.");
        }

        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com o ID: " + id));

        avaliacao.setValor(avaliacaoDetails.getValor());
        avaliacao.setProfissional(avaliacaoDetails.getProfissional());
        avaliacao.setDescricao(avaliacaoDetails.getDescricao());
        avaliacao.setData(avaliacaoDetails.getData());
        avaliacao.setServico(avaliacaoDetails.getServico());

        return avaliacaoRepository.save(avaliacao);
    }

    /**
     * Apaga uma avaliação pelo ID.
     *
     * @param id o ID da avaliação a ser apagada
     * @throws IllegalArgumentException se o ID for nulo
     */
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        avaliacaoRepository.deleteById(id);
    }

}