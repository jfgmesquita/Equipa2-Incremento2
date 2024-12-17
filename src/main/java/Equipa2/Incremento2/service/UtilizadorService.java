package Equipa2.Incremento2.service;

import java.util.UUID;
import java.util.List;

import Equipa2.Incremento2.model.enums.StatusAtivo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import Equipa2.Incremento2.model.enums.UserType;
import Equipa2.Incremento2.model.Admin;
import Equipa2.Incremento2.model.Cliente;
import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.UtilizadorDTO;
import Equipa2.Incremento2.repository.AdminRepository;
import Equipa2.Incremento2.repository.ClienteRepository;
import Equipa2.Incremento2.repository.ProfissionalRepository;
import Equipa2.Incremento2.repository.UtilizadorRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

/**
 * Serviço para operações relacionadas com a entidade Utilizador.
 */
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

    /**
     * Encontra todos os utilizadores.
     *
     * @return uma lista de todos os utilizadores
     */
    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    /**
     * Encontra um utilizador pelo ID.
     *
     * @param id o ID do utilizador
     * @return o utilizador com o ID fornecido
     * @throws IllegalArgumentException se o ID for nulo
     * @throws ResourceNotFoundException se o utilizador não for encontrado
     */
    public Utilizador findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return utilizadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado com o ID: " + id));
    }

    /**
     * Encontra um utilizador pelo ID e converte-o para um DTO.
     *
     * @param id o ID do utilizador
     * @return o DTO do utilizador com o ID fornecido
     * @throws IllegalArgumentException se o ID for nulo
     */
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
        utilizadorDTO.setStatus(uti.getStatus());
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

    /**
     * Encontra um utilizador pelo email.
     *
     * @param email o email do utilizador
     * @return o utilizador com o email fornecido
     */
    public Utilizador findByEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

     /**
    * Encontra todos os utilizadores de um tipo.
    *
    * @param tipo o tipo de utilizador
    * @return uma lista de todos os utilizadores com esse tipo
    * @throws IllegalArgumentException se o tipo for nulo
    */
    public List<Utilizador> findAllByTipo(String tipo){
        if (tipo == null) {
            throw new IllegalArgumentException("tipo não pode ser nulo.");
        }
        return utilizadorRepository.findAllByUserType(tipo);
    }

    /**
     * Guarda um cliente.
     *
     * @param cliente o cliente a ser guardado
     * @return o cliente guardado
     * @throws IllegalArgumentException se o cliente for nulo
     */
    public Cliente saveCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }

        return clienteRepository.save(cliente);
    }

    /**
     * Guarda um profissional.
     *
     * @param profissional o profissional a ser guardado
     * @return o profissional guardado
     * @throws IllegalArgumentException se o profissional for nulo
     */
    public Profissional saveProfissional(Profissional profissional) {
        if (profissional == null) {
            throw new IllegalArgumentException("Profissional não pode ser nulo.");
        }

        return profissionalRepository.save(profissional);
    }

    /**
     * Guarda um administrador.
     *
     * @param admin o administrador a ser guardado
     * @return o administrador guardado
     * @throws IllegalArgumentException se o administrador for nulo
     */
    public Admin saveAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin não pode ser nulo.");
        }

        return adminRepository.save(admin);
    }

    /**
     * Atualiza um utilizador.
     *
     * @param id o ID do utilizador a ser atualizado
     * @param utilizadorDetails os detalhes do utilizador a serem atualizados
     * @return o utilizador atualizado
     * @throws IllegalArgumentException se o ID ou os detalhes do utilizador forem nulos
     * @throws ResourceNotFoundException se o utilizador não for encontrado
     */
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


    public Utilizador disableUtilizador(Utilizador utilizador){
        utilizador.setStatus(StatusAtivo.INATIVO);
        return utilizadorRepository.save(utilizador);
    }


    /**
     * Apaga um utilizador pelo ID.
     *
     * @param id o ID do utilizador a ser apagado
     * @throws IllegalArgumentException se o ID for nulo
     * @throws ResourceNotFoundException se o utilizador não for encontrado
     */
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
