package Equipa2.Incremento2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import Equipa2.Incremento2.model.dto.UtilizadorDTO;
import Equipa2.Incremento2.service.UtilizadorService;
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
    private UtilizadorService utilizadorService;

//    @GetMapping
//    public List<ServicoDTO> getAllServicos() {
//        ListzservicoService.findAll();
//        return
//    }

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
                    ser.getId(),
                    ser.getTitulo(),
                    ser.getDescricao(),
                    ser.getData(),
                    ser.getValorHora(),
                    utilizadorService.findDTOById(ser.getProfissional().getId())
            ));
        }

        return ResponseEntity.ok(servicoDTOs);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@RequestBody ServicoDTO servico) {
        Profissional pro = (Profissional) utilizadorService.findById(servico.getProfissional().getId());

        Servico ser = new Servico();
        ser.setTitulo(servico.getTitulo());
        ser.setDescricao(servico.getDescricao());
        ser.setData(servico.getData());
        ser.setValorHora(servico.getValorHora());
        ser.setProfissional(pro);

        servicoService.save(ser);

        servico.setId(ser.getId());
        servico.getProfissional().setNome(pro.getNome());
        servico.getProfissional().setEmail(pro.getEmail());
        servico.getProfissional().setPassword(pro.getNome());
        servico.getProfissional().setMorada(pro.getMorada());
        servico.getProfissional().setUserType(pro.getUserType());
        servico.getProfissional().setFormaDePagamento(pro.getFormaDePagamento());
        servico.getProfissional().setEspecialidade(pro.getNome());
        servico.getProfissional().setExperiencia(pro.getExperiencia());

        return ResponseEntity.ok().body(servico);
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
