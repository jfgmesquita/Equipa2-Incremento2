package Equipa2.Incremento2.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.LoginRequestDTO;
import Equipa2.Incremento2.model.dto.UtilizadorDTO;
import Equipa2.Incremento2.service.UtilizadorService;

/**
 * Controlador para operações relacionadas com a autenticação.
 */
@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UtilizadorService utilizadorService;

    /**
     * Autentica um utilizador.
     *
     * @param loginRequest os dados de autenticação
     * @return o utilizador autenticado
     */
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest){
        Utilizador utilizador = utilizadorService.findByEmail(loginRequest.getEmail());
        UtilizadorDTO userDto = utilizadorService.findDTOById(utilizador.getId());


        if(utilizador == null || !loginRequest.getPassword().equals(utilizador.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        return ResponseEntity.ok().body(userDto);
    }

}
