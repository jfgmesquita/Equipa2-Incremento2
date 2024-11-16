package Equipa2.Incremento2.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Solicitacao;
import Equipa2.Incremento2.service.SolicitacaoService;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {
    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public List<Solicitacao> getAllSolicitacoes() {
        return solicitacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> getSolicitacaoById(@PathVariable UUID id) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solicitacao);
    }

    @PostMapping
    public Solicitacao createSolicitacao(@RequestBody Solicitacao solicitacao) {
        return solicitacaoService.save(solicitacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitacao> updateSolicitacao(@PathVariable UUID id, @RequestBody Solicitacao solicitacaoDetails) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        solicitacao.setStatus(solicitacaoDetails.getStatus());
        solicitacao.setMorada(solicitacaoDetails.getMorada());
        solicitacao.setData(solicitacaoDetails.getData());
        Solicitacao updatedSolicitacao = solicitacaoService.save(solicitacao);

        return ResponseEntity.ok(updatedSolicitacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable UUID id) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        solicitacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
