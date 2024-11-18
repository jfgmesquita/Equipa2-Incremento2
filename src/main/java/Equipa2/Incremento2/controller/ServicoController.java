package Equipa2.Incremento2.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import Equipa2.Incremento2.repository.UtilizadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.service.ServicoService;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @GetMapping
    public List<Servico> getAllServicos() {
        return servicoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServicoById(@PathVariable UUID id) {
        Servico servico = servicoService.findById(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servico);
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<Servico>> getAllByProfissionalId(@PathVariable UUID profissionalId){
        List<Servico> servicos = servicoService.findAllByProfissionalId(profissionalId);

        return ResponseEntity.ok(servicos);
    }

    @PostMapping
    public Servico createServico(@RequestBody ServicoDTO servico) {
        Optional<Utilizador> pro = utilizadorRepository.findById(servico.getProfissional());

        Servico ser = new Servico();
        ser.setTitulo(servico.getTitulo());
        ser.setDescricao(servico.getDescricao());
        ser.setData(servico.getData());
        ser.setValorHora(servico.getValorHora());
        ser.setProfissional((Profissional) pro.get());

        return servicoService.save(ser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> updateServico(@PathVariable UUID id, @RequestBody Servico servicoDetails) {
        Servico servico = servicoService.findById(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        servico.setTitulo(servicoDetails.getTitulo());
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setData(servicoDetails.getData());
        servico.setValorHora(servicoDetails.getValorHora());
        Servico updatedServico = servicoService.save(servico);

        return ResponseEntity.ok(updatedServico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable UUID id) {
        Servico servico = servicoService.findById(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }
        
        servicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
