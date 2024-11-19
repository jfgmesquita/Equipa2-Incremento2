package Equipa2.Incremento2.controller;

import java.util.UUID;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Pagamento;
import Equipa2.Incremento2.service.PagamentoService;

/**
 * Controlador para operações relacionadas com a entidade Pagamento.
 */
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public List<Pagamento> getAllPagamentos() {
        return pagamentoService.findAll();
    }

    /**
     * Encontra um pagamento pelo ID.
     * 
     * @param id o ID do pagamento
     * @return o pagamento com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable UUID id) {
        Pagamento pagamento = pagamentoService.findById(id);

        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pagamento);
    }

    /**
     * Cria um pagamento.
     * 
     * @param pagamento o pagamento a ser criado
     * @return o pagamento criado
     */
    @PostMapping
    public Pagamento createPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoService.save(pagamento);
    }

    /**
     * Atualiza um pagamento.
     * 
     * @param id o ID do pagamento
     * @param pagamentoDetails os detalhes do pagamento a serem atualizados
     * @return o pagamento atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable UUID id, @RequestBody Pagamento pagamentoDetails) {
        Pagamento pagamento = pagamentoService.findById(id);

        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        pagamento.setValor(pagamentoDetails.getValor());
        pagamento.setOrigemCliente(pagamentoDetails.getOrigemCliente());
        pagamento.setDestinoProfissional(pagamentoDetails.getDestinoProfissional());
        pagamento.setMetodo(pagamentoDetails.getMetodo());
        pagamento.setStatus(pagamentoDetails.getStatus());
        Pagamento updatedPagamento = pagamentoService.save(pagamento);

        return ResponseEntity.ok(updatedPagamento);
    }

    /**
     * Apaga um pagamento.
     * 
     * @param id o ID do pagamento
     * @return uma resposta de sucesso ou erro
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable UUID id) {
        Pagamento pagamento = pagamentoService.findById(id);

        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }
        
        pagamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
