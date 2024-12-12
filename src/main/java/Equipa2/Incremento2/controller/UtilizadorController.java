package Equipa2.Incremento2.controller;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.enums.UserType;
import Equipa2.Incremento2.model.Admin;
import Equipa2.Incremento2.model.Cliente;
import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.UtilizadorDTO;
import Equipa2.Incremento2.service.UtilizadorService;

/**
 * Controlador para operações relacionadas com a entidade Utilizador.
 */
@RestController
@RequestMapping("/api/utilizadores")
@CrossOrigin(origins = "*")
public class UtilizadorController {

    @Autowired
    private UtilizadorService utilizadorService;

    /**
     * Encontra todos os utilizadores.
     *
     * @return uma lista de todos os utilizadores
     */
    @GetMapping
    public ResponseEntity<List<UtilizadorDTO>> getAllUtilizadores() {
        List<Utilizador> utilizadores = utilizadorService.findAll();

        List<UtilizadorDTO> dtos = new ArrayList<>();

        for(Utilizador uti : utilizadores){
            dtos.add(
                    new UtilizadorDTO(
                            uti.getId(),
                            uti.getNome(),
                            uti.getEmail(),
                            uti.getPassword(),
                            uti.getMorada(),
                            uti.getUserType()
                    )
            );
        }

        return ResponseEntity.ok().body(dtos);
    }

    /**
     * Encontra um utilizador pelo ID.
     *
     * @param id o ID do utilizador
     * @return o utilizador com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtilizadorDTO> getUtilizadorById(@PathVariable UUID id) {
        UtilizadorDTO utilizador = utilizadorService.findDTOById(id);

        if (utilizador == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(utilizador);
    }

  /**
     * Encontra todos os Utilizadores de um tipo.
     *
     * @param tipo o tipo de Utilizador
     * @return uma lista de todos os utilizadores com esse tipo
     */
    @GetMapping("/tipo")
    public ResponseEntity<List<UtilizadorDTO>> getAllUtilizadoresByUserType(@RequestParam(value="UserType") UserType UserType){
        List<Utilizador> utilizadores = utilizadorService.findAllByTipo(UserType.name());
       
        List<UtilizadorDTO> dtos = new ArrayList<>();

        for(Utilizador uti : utilizadores){
            UtilizadorDTO utilizadorDTO = new UtilizadorDTO(
                uti.getId(),
                uti.getNome(),
                uti.getEmail(),
                uti.getPassword(),
                uti.getMorada(),
                uti.getUserType()
        );
            dtos.add(utilizadorDTO);
        }
        return ResponseEntity.ok().body(dtos);
        
    }

    /**
     * Cria um utilizador.
     *
     * @param utilizador o utilizador a ser criado
     * @return o utilizador criado
     */
    @PostMapping
    public ResponseEntity<?> createUtilizador(@RequestBody UtilizadorDTO utilizador){

        if(utilizadorService.findByEmail(utilizador.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um utilizador registado com este email.");
        }

        if(utilizador.getUserType() == UserType.CLIENTE){
            Cliente cliente = new Cliente();
            cliente.setNome(utilizador.getNome());
            cliente.setEmail(utilizador.getEmail());
            cliente.setPassword(utilizador.getPassword());
            cliente.setMorada(utilizador.getMorada());
            cliente.setUserType(utilizador.getUserType());
            cliente.setFormaDePagamento(utilizador.getFormaDePagamento());
            utilizadorService.saveCliente(cliente);
            return ResponseEntity.ok().body(new UtilizadorDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getPassword(),
                    cliente.getMorada(),
                    cliente.getUserType(),
                    cliente.getFormaDePagamento()
            ));
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
            return ResponseEntity.ok().body(new UtilizadorDTO(
                    profissional.getId(),
                    profissional.getNome(),
                    profissional.getEmail(),
                    profissional.getPassword(),
                    profissional.getMorada(),
                    profissional.getUserType(),
                    profissional.getFormaDePagamento(),
                    profissional.getEspecialidade(),
                    profissional.getExperiencia()
            ));
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
            return ResponseEntity.ok().body(new UtilizadorDTO(
                    admin.getId(),
                    admin.getNome(),
                    admin.getEmail(),
                    admin.getPassword(),
                    admin.getMorada(),
                    admin.getUserType(),
                    admin.getCodigo()
            ));
        }

        return ResponseEntity.badRequest().build();

    }

    /**
     * Atualiza um utilizador.
     *
     * @param id o ID do utilizador
     * @param utilizadorDetails os detalhes do utilizador a ser atualizado
     * @return o utilizador atualizado
     */
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

    /**
     * Apaga um utilizador.
     *
     * @param id o ID do utilizador
     * @return uma resposta de sucesso ou erro
     */
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