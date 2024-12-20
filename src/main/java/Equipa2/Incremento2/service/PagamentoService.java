package Equipa2.Incremento2.service;

import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.List;

import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.dto.PagamentoDTO;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Pagamento;
import Equipa2.Incremento2.repository.PagamentoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

/**
 * Serviço para operações relacionadas com a entidade Pagamento.
 */
@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    /**
     * Encontra todos os pagamentos.
     *
     * @return uma lista de todos os pagamentos
     */
    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    /**
     * Encontra um pagamento pelo ID.
     *
     * @param id o ID do pagamento
     * @return o pagamento com o ID fornecido
     * @throws IllegalArgumentException se o ID for nulo
     * @throws ResourceNotFoundException se o pagamento não for encontrado
     */
    public Pagamento findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com o ID: " + id));
    }

    public PagamentoDTO findDTOById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Pagamento pag = pagamentoRepository.findById(id).get();


        return new PagamentoDTO(
                pag.getId(),
                pag.getValor(),
                pag.getSolicitacao().getId(),
                pag.getOrigemCliente().getId(),
                pag.getDestinoProfissional().getId(),
                pag.getStatus(),
                (int) (pag.getValor() / pag.getSolicitacao().getServico().getValorHora())
        );
    }

    /**
     * Guarda um pagamento.
     *
     * @param pagamento o pagamento a ser guardado
     * @return o pagamento guardado
     * @throws IllegalArgumentException se o pagamento for nulo
     */
    public PagamentoDTO save(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
        }

        pagamentoRepository.save(pagamento);

        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getValor(),
                pagamento.getSolicitacao().getId(),
                pagamento.getOrigemCliente().getId(),
                pagamento.getDestinoProfissional().getId(),
                pagamento.getStatus(),
                0
        );
    }

//    /**
//     * Atualiza um pagamento.
//     *
//     * @param id o ID do pagamento a ser atualizado
//     * @param pagamentoDetails os detalhes do pagamento a serem atualizados
//     * @return o pagamento atualizado
//     * @throws IllegalArgumentException se o ID ou os detalhes do pagamento forem nulos
//     * @throws ResourceNotFoundException se o pagamento não for encontrado
//     */
//    public Pagamento update(UUID id, Pagamento pagamentoDetails) {
//        if (id == null) {
//            throw new IllegalArgumentException("ID não pode ser nulo.");
//        }
//
//        if (pagamentoDetails == null) {
//            throw new IllegalArgumentException("Detalhes do pagamento não podem ser nulos.");
//        }
//
//        Pagamento pagamento = pagamentoRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com o ID: " + id));
//
//        pagamento.setValor(pagamentoDetails.getValor());
//        pagamento.setOrigemCliente(pagamentoDetails.getOrigemCliente());
//        pagamento.setDestinoProfissional(pagamentoDetails.getDestinoProfissional());
//        pagamento.setMetodo(pagamentoDetails.getMetodo());
//        pagamento.setStatus(pagamentoDetails.getStatus());
//
//        return pagamentoRepository.save(pagamento);
//    }

    /**
     * Apaga um pagamento pelo ID.
     *
     * @param id o ID do pagamento a ser apagado
     * @throws IllegalArgumentException se o ID for nulo
     */
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        pagamentoRepository.deleteById(id);
    }

}
