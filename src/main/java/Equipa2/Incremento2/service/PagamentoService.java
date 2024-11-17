package Equipa2.Incremento2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Pagamento;
import Equipa2.Incremento2.repository.PagamentoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    public Pagamento findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com o ID: " + id));
    }

    public Pagamento save(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
        }

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento update(UUID id, Pagamento pagamentoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (pagamentoDetails == null) {
            throw new IllegalArgumentException("Detalhes do pagamento não podem ser nulos.");
        }

        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com o ID: " + id));
        
        pagamento.setValor(pagamentoDetails.getValor());
        pagamento.setOrigemCliente(pagamentoDetails.getOrigemCliente());
        pagamento.setDestinoProfissional(pagamentoDetails.getDestinoProfissional());
        pagamento.setMetodo(pagamentoDetails.getMetodo());
        pagamento.setStatus(pagamentoDetails.getStatus());

        return pagamentoRepository.save(pagamento);
    }

    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        pagamentoRepository.deleteById(id);
    }
}
