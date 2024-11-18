package Equipa2.Incremento2.controller;

import java.util.List;
import java.util.UUID;

import Equipa2.Incremento2.model.Admin;
import Equipa2.Incremento2.model.Cliente;
import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.dto.UtilizadorDTO;
import Equipa2.Incremento2.model.enums.UserType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.service.UtilizadorService;

@RestController
@RequestMapping("/api/utilizadores")
@CrossOrigin(origins = "*")
public class UtilizadorController {
    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping
    public List<Utilizador> getAllUtilizadores() {
        return utilizadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilizadorDTO> getUtilizadorById(@PathVariable UUID id) {
        UtilizadorDTO utilizador = utilizadorService.findDTOById(id);

        if (utilizador == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(utilizador);
    }

    @PostMapping
    public ResponseEntity<Utilizador> createUtilizador(@RequestBody UtilizadorDTO utilizador){

        if(utilizador.getUserType() == UserType.CLIENTE){
            Cliente cliente = new Cliente();
            cliente.setNome(utilizador.getNome());
            cliente.setEmail(utilizador.getEmail());
            cliente.setPassword(utilizador.getPassword());
            cliente.setMorada(utilizador.getMorada());
            cliente.setUserType(utilizador.getUserType());
            cliente.setFormaDePagamento(utilizador.getFormaDePagamento());
            utilizadorService.saveCliente(cliente);
            return ResponseEntity.ok().body(cliente);
        }

        if(utilizador.getUserType() == UserType.PROFISSIONAL){
            Profissional profissional = new Profissional();
            profissional.setNome(utilizador.getNome());
            profissional.setEmail(utilizador.getEmail());
            profissional.setPassword(utilizador.getPassword());
            profissional.setMorada(utilizador.getMorada());
            profissional.setUserType(utilizador.getUserType());
            profissional.setFormaDePagamento(utilizador.getFormaDePagamento());
            profissional.setEspecialidade(utilizador.getEspecialidade());
            profissional.setExperiencia(utilizador.getExperiencia());
            utilizadorService.saveProfissional(profissional);
            return ResponseEntity.ok().body(profissional);
        }

        if(utilizador.getUserType() == UserType.ADMINISTRADOR){
            Admin admin = new Admin();
            admin.setNome(utilizador.getNome());
            admin.setEmail(utilizador.getEmail());
            admin.setPassword(utilizador.getPassword());
            admin.setMorada(utilizador.getMorada());
            admin.setUserType(utilizador.getUserType());
            admin.setCodigo(utilizador.getCodigo());
            utilizadorService.saveAdmin(admin);
            return ResponseEntity.ok().body(admin);
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilizador> updateUtilizador(@PathVariable UUID id, @RequestBody UtilizadorDTO utilizadorDetails) {
        Utilizador utilizador = utilizadorService.findById(id);

        if (utilizador == null) {
            return ResponseEntity.notFound().build();
        }

        if(utilizador.getUserType() == UserType.CLIENTE){
            Cliente cliente = (Cliente) utilizador;
            cliente.setNome(utilizadorDetails.getNome());
            cliente.setEmail(utilizadorDetails.getEmail());
            cliente.setPassword(utilizadorDetails.getPassword());
            cliente.setMorada(utilizadorDetails.getMorada());
            cliente.setUserType(utilizadorDetails.getUserType());
            cliente.setFormaDePagamento(utilizadorDetails.getFormaDePagamento());
            utilizadorService.saveCliente(cliente);
            return ResponseEntity.ok().body(cliente);
        }

        if(utilizador.getUserType() == UserType.PROFISSIONAL){
            Profissional profissional = (Profissional) utilizador;
            profissional.setNome(utilizadorDetails.getNome());
            profissional.setEmail(utilizadorDetails.getEmail());
            profissional.setPassword(utilizadorDetails.getPassword());
            profissional.setMorada(utilizadorDetails.getMorada());
            profissional.setUserType(utilizadorDetails.getUserType());
            profissional.setFormaDePagamento(utilizadorDetails.getFormaDePagamento());
            profissional.setEspecialidade(utilizadorDetails.getEspecialidade());
            profissional.setExperiencia(utilizadorDetails.getExperiencia());
            utilizadorService.saveProfissional(profissional);
            return ResponseEntity.ok().body(profissional);
        }

        if(utilizador.getUserType() == UserType.ADMINISTRADOR){
            Admin admin = (Admin) utilizador;
            admin.setNome(utilizadorDetails.getNome());
            admin.setEmail(utilizadorDetails.getEmail());
            admin.setPassword(utilizadorDetails.getPassword());
            admin.setMorada(utilizadorDetails.getMorada());
            admin.setUserType(utilizadorDetails.getUserType());
            admin.setCodigo(utilizadorDetails.getCodigo());
            utilizadorService.saveAdmin(admin);
            return ResponseEntity.ok().body(admin);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilizador(@PathVariable UUID id) {
        Utilizador utilizador = utilizadorService.findById(id);

        if (utilizador == null) {
            return ResponseEntity.notFound().build();
        }

        utilizadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}