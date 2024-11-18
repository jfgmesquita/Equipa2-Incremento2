package Equipa2.Incremento2.controller;

import java.util.ArrayList;
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
    public List<ServicoDTO> getAllServicos() {
        List<Servico> servicos = servicoService.findAll();

        List<ServicoDTO> servicoDTOs = new ArrayList<>();

        for(Servico ser: servicos){
            servicoDTOs.add(new ServicoDTO(
                    ser.getTitulo(),
                    ser.getDescricao(),
                    ser.getData(),
                    ser.getValorHora(),
                    ser.getProfissional().getId()
            ));
        }

        return servicoDTOs;
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
    public ResponseEntity<List<ServicoDTO>> getAllByProfissionalId(@PathVariable UUID profissionalId){
        List<Servico> servicos = servicoService.findAllByProfissionalId(profissionalId);

        List<ServicoDTO> servicoDTOs = new ArrayList<>();

        for(Servico ser: servicos){
            servicoDTOs.add(new ServicoDTO(
                    ser.getTitulo(),
                    ser.getDescricao(),
                    ser.getData(),
                    ser.getValorHora(),
                    ser.getProfissional().getId()
            ));
        }

        return ResponseEntity.ok(servicoDTOs);
    }

    @PostMapping
    public ServicoDTO createServico(@RequestBody ServicoDTO servico) {
        Optional<Utilizador> pro = utilizadorRepository.findById(servico.getProfissionalId());

        Servico ser = new Servico();
        ser.setTitulo(servico.getTitulo());
        ser.setDescricao(servico.getDescricao());
        ser.setData(servico.getData());
        ser.setValorHora(servico.getValorHora());
        ser.setProfissional((Profissional) pro.get());

        servicoService.save(ser);

        return servico;
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
