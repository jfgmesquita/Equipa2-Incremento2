package Equipa2.Incremento2.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.service.UtilizadorService;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {
    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping
    public List<Utilizador> getAllUtilizadores() {
        return utilizadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> getUtilizadorById(@PathVariable UUID id) {
        Utilizador utilizador = utilizadorService.findById(id);

        if (utilizador == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(utilizador);
    }

    @PostMapping
    public Utilizador createUtilizador(@RequestBody Utilizador utilizador) {
        return utilizadorService.save(utilizador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilizador> updateUtilizador(@PathVariable UUID id, @RequestBody Utilizador utilizadorDetails) {
        Utilizador utilizador = utilizadorService.findById(id);
        
        if (utilizador == null) {
            return ResponseEntity.notFound().build();
        }

        utilizador.setNome(utilizadorDetails.getNome());
        utilizador.setEmail(utilizadorDetails.getEmail());
        utilizador.setPassword(utilizadorDetails.getPassword());
        utilizador.setMorada(utilizadorDetails.getMorada());
        utilizador.setUserType(utilizadorDetails.getUserType());
        Utilizador updatedUtilizador = utilizadorService.save(utilizador);

        return ResponseEntity.ok(updatedUtilizador);
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