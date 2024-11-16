package Equipa2.Incremento2.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Avaliacao;
import Equipa2.Incremento2.service.AvaliacaoService;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable UUID id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);

        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avaliacao);
    }

    @PostMapping
    public Avaliacao createAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.save(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable UUID id, @RequestBody Avaliacao avaliacaoDetails) {
        Avaliacao avaliacao = avaliacaoService.findById(id);

        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }

        avaliacao.setValor(avaliacaoDetails.getValor());
        avaliacao.setDescricao(avaliacaoDetails.getDescricao());
        avaliacao.setData(avaliacaoDetails.getData());
        Avaliacao updatedAvaliacao = avaliacaoService.save(avaliacao);

        return ResponseEntity.ok(updatedAvaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable UUID id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);

        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }

        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
