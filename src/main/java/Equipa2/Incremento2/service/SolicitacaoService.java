package Equipa2.Incremento2.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Solicitacao;
import Equipa2.Incremento2.repository.SolicitacaoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

/**
 * Serviço para operações relacionadas com a entidade Solicitacao.
 */
@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    /**
     * Encontra todas as solicitações.
     *
     * @return uma lista de todas as solicitações
     */
    public List<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    /**
     * Encontra uma solicitação pelo ID.
     *
     * @param id o ID da solicitação
     * @return a solicitação com o ID fornecido
     * @throws IllegalArgumentException se o ID for nulo
     * @throws ResourceNotFoundException se a solicitação não for encontrada
     */
    public Solicitacao findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return solicitacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada com o ID: " + id));
    }

    /**
     * Guarda uma solicitação.
     *
     * @param solicitacao a solicitação a ser guardada
     * @return a solicitação guardada
     * @throws IllegalArgumentException se a solicitação for nula
     */
    public Solicitacao save(Solicitacao solicitacao) {
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação não pode ser nula.");
        }

        return solicitacaoRepository.save(solicitacao);
    }

    /**
     * Atualiza uma solicitação.
     *
     * @param id o ID da solicitação a ser atualizada
     * @param solicitacaoDetails os detalhes da solicitação a serem atualizados
     * @return a solicitação atualizada
     * @throws IllegalArgumentException se o ID ou os detalhes da solicitação forem nulos
     * @throws ResourceNotFoundException se a solicitação não for encontrada
     */
    public Solicitacao update(UUID id, Solicitacao solicitacaoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (solicitacaoDetails == null) {
            throw new IllegalArgumentException("Detalhes da solicitação não podem ser nulos.");
        }

        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada com o ID: " + id));

        solicitacao.setStatus(solicitacaoDetails.getStatus());
        solicitacao.setCliente(solicitacaoDetails.getCliente());
        solicitacao.setProfissional(solicitacaoDetails.getProfissional());
        solicitacao.setServico(solicitacaoDetails.getServico());
        solicitacao.setPagamento(solicitacaoDetails.getPagamento());
        solicitacao.setData(solicitacaoDetails.getData());

        return solicitacaoRepository.save(solicitacao);
    }

    /**
     * Apaga uma solicitação pelo ID.
     *
     * @param id o ID da solicitação a ser apagada
     * @throws IllegalArgumentException se o ID for nulo
     * @throws ResourceNotFoundException se a solicitação não for encontrada
     */
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        if (!solicitacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Solicitação não encontrada com o ID: " + id);
        }
        solicitacaoRepository.deleteById(id);
    }

}
