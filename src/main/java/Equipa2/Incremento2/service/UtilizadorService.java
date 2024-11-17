package Equipa2.Incremento2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.repository.UtilizadorRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

@Service
public class UtilizadorService {
    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Utilizador findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return utilizadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado com o ID: " + id));
    }

    public Utilizador save(Utilizador utilizador) {
        if (utilizador == null) {
            throw new IllegalArgumentException("Utilizador não pode ser nulo.");
        }

        return utilizadorRepository.save(utilizador);
    }
    
    public Utilizador update(UUID id, Utilizador utilizadorDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (utilizadorDetails == null) {
            throw new IllegalArgumentException("Detalhes do utilizador não podem ser nulos.");
        }

        Utilizador utilizador = utilizadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado com o ID: " + id));
        
        utilizador.setNome(utilizadorDetails.getNome());
        utilizador.setEmail(utilizadorDetails.getEmail());
        utilizador.setPassword(utilizadorDetails.getPassword());
        utilizador.setMorada(utilizadorDetails.getMorada());
        utilizador.setUserType(utilizadorDetails.getUserType());

        return utilizadorRepository.save(utilizador);
    }

    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (!utilizadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilizador não encontrado com o ID: " + id);
        }
        
        utilizadorRepository.deleteById(id);
    }
}
