package Equipa2.Incremento2.controller;

import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.LoginRequestDTO;
import Equipa2.Incremento2.service.AuthService;
import Equipa2.Incremento2.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtilizadorService utilizadorService;

    @PostMapping
    public ResponseEntity<Utilizador> login(@RequestBody LoginRequestDTO loginRequest){
        Utilizador utilizador = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if(utilizador != null){
            return ResponseEntity.ok().body(utilizador);
        } else{
            return ResponseEntity.badRequest().build();
        }
    }
}
