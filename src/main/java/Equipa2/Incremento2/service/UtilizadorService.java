package Equipa2.Incremento2.service;

import java.util.List;
import java.util.UUID;

import Equipa2.Incremento2.model.Admin;
import Equipa2.Incremento2.model.Cliente;
import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.dto.UtilizadorDTO;
import Equipa2.Incremento2.model.enums.UserType;
import Equipa2.Incremento2.repository.AdminRepository;
import Equipa2.Incremento2.repository.ClienteRepository;
import Equipa2.Incremento2.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.repository.UtilizadorRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

@Service
public class UtilizadorService {
    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Utilizador findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return utilizadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado com o ID: " + id));
    }

    public UtilizadorDTO findDTOById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Utilizador uti = utilizadorRepository.findById(id).get();

        UtilizadorDTO utilizadorDTO = new UtilizadorDTO();
        utilizadorDTO.setId(uti.getId());
        utilizadorDTO.setNome(uti.getNome());
        utilizadorDTO.setEmail(uti.getEmail());
        utilizadorDTO.setPassword(uti.getPassword());
        utilizadorDTO.setMorada(uti.getMorada());
        utilizadorDTO.setUserType(uti.getUserType());
        if(uti.getUserType() == UserType.CLIENTE){
            Cliente cli = (Cliente) uti;
            utilizadorDTO.setFormaDePagamento(cli.getFormaDePagamento());
        }

        if (uti.getUserType() == UserType.PROFISSIONAL) {
            Profissional pro = (Profissional) uti;
            utilizadorDTO.setFormaDePagamento(pro.getFormaDePagamento());
            utilizadorDTO.setEspecialidade(pro.getEspecialidade());
            utilizadorDTO.setExperiencia(pro.getExperiencia());
        }

        if (uti.getUserType() == UserType.ADMINISTRADOR){
            Admin adm = (Admin) uti;
            utilizadorDTO.setCodigo(adm.getCodigo());
        }

        return utilizadorDTO;
    }

    public Utilizador findByEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

    public Cliente saveCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }

        return clienteRepository.save(cliente);
    }

    public Profissional saveProfissional(Profissional pro) {
        if (pro == null) {
            throw new IllegalArgumentException("Profissional não pode ser nulo.");
        }

        return profissionalRepository.save(pro);
    }

    public Admin saveAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin não pode ser nulo.");
        }

        return adminRepository.save(admin);
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
