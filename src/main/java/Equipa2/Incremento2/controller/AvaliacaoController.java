package Equipa2.Incremento2.controller;

import java.util.UUID;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Avaliacao;
import Equipa2.Incremento2.service.AvaliacaoService;

/**
 * Controlador para operações relacionadas com a entidade Avaliacao.
 */
@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    /**
     * Encontra todas as avaliações.
     *
     * @return uma lista de todas as avaliações
     */
    @GetMapping
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoService.findAll();
    }

    /**
     * Encontra uma avaliação pelo ID.
     * 
     * @param id o ID da avaliação
     * @return a avaliação com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable UUID id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);

        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avaliacao);
    }

    /**
     * Cria uma avaliação.
     * 
     * @param avaliacao a avaliação a ser criada
     * @return a avaliação criada
     */
    @PostMapping
    public Avaliacao createAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.save(avaliacao);
    }

    /**
     * Atualiza uma avaliação.
     * 
     * @param id o ID da avaliação
     * @param avaliacaoDetails os detalhes da avaliação a serem atualizados
     * @return a avaliação atualizada
     */
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

    /**
     * Apaga uma avaliação.
     * 
     * @param id o ID da avaliação
     * @return uma resposta de sucesso ou erro
     */
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
