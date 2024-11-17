package Equipa2.Incremento2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Solicitacao;
import Equipa2.Incremento2.repository.SolicitacaoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    public List<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    public Solicitacao findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return solicitacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada com o ID: " + id));
    }

    public Solicitacao save(Solicitacao solicitacao) {
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação não pode ser nula.");
        }

        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao update(UUID id, Solicitacao solicitacaoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (solicitacaoDetails == null) {
            throw new IllegalArgumentException("Detalhes da solicitação não podem ser nulos.");
        }

        Solicitacao solicitacao = solicitacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada com o ID: " + id));
        
        solicitacao.setStatus(solicitacaoDetails.getStatus());
        solicitacao.setMorada(solicitacaoDetails.getMorada());
        solicitacao.setData(solicitacaoDetails.getData());

        return solicitacaoRepository.save(solicitacao);
    }

    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        solicitacaoRepository.deleteById(id);
    }
}
