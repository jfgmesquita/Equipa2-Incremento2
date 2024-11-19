package Equipa2.Incremento2.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import Equipa2.Incremento2.model.*;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.repository.ServicoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UtilizadorService utilizadorService;

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    public ServicoDTO findDTOById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Servico ser = servicoRepository.findById(id).get();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String formattedDate = ser.getData().format(dtf);

        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setId(ser.getId());
        servicoDTO.setTitulo(ser.getTitulo());
        servicoDTO.setDescricao(ser.getDescricao());
        servicoDTO.setData(formattedDate);
        servicoDTO.setProfissional(utilizadorService.findDTOById(ser.getProfissional().getId()));

        return servicoDTO;
    }

    public Servico findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));
    }

    public List<Servico> findAllByProfissionalId(UUID profissionalId){
        if (profissionalId == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return servicoRepository.findAllByProfissionalId(profissionalId);
    }

    public Servico save(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo.");
        }

        return servicoRepository.save(servico);
    }

    public Servico update(UUID id, Servico servicoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (servicoDetails == null) {
            throw new IllegalArgumentException("Detalhes do serviço não podem ser nulos.");
        }

        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));
        
        servico.setTitulo(servicoDetails.getTitulo());
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setData(servicoDetails.getData());
        servico.setValorHora(servicoDetails.getValorHora());

        return servicoRepository.save(servico);
    }

    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        servicoRepository.deleteById(id);
    }
}
