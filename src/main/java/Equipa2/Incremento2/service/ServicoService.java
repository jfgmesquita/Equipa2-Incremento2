package Equipa2.Incremento2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.repository.ServicoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    public Servico findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));
    }

    public Servico save(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo.");
        }

        return servicoRepository.save(servico);
    }

    public Servico update(UUID id, Servico servicoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (servicoDetails == null) {
            throw new IllegalArgumentException("Detalhes do serviço não podem ser nulos.");
        }

        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));
        
        servico.setTitulo(servicoDetails.getTitulo());
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setData(servicoDetails.getData());
        servico.setValorHora(servicoDetails.getValorHora());

        return servicoRepository.save(servico);
    }

    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        servicoRepository.deleteById(id);
    }
}
