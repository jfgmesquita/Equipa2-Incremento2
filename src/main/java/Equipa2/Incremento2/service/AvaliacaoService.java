package Equipa2.Incremento2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Avaliacao;
import Equipa2.Incremento2.repository.AvaliacaoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return avaliacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com o ID: " + id));
    }

    public Avaliacao save(Avaliacao avaliacao) {
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não pode ser nula.");
        }

        return avaliacaoRepository.save(avaliacao);
    }
    
    public Avaliacao update(UUID id, Avaliacao avaliacaoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (avaliacaoDetails == null) {
            throw new IllegalArgumentException("Detalhes da avaliação não podem ser nulos.");
        }

        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com o ID: " + id));
        
        avaliacao.setValor(avaliacaoDetails.getValor());
        avaliacao.setDescricao(avaliacaoDetails.getDescricao());
        avaliacao.setData(avaliacaoDetails.getData());

        return avaliacaoRepository.save(avaliacao);
    }

    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        avaliacaoRepository.deleteById(id);
    }
}