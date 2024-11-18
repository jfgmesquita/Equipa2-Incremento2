package Equipa2.Incremento2.service;

import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.LoginRequestDTO;
import Equipa2.Incremento2.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public Utilizador login(String email, String password){
        Utilizador utilizador = utilizadorRepository.findByEmail(email);

        if(utilizador.getPassword().equals(password)){
            return utilizador;
        }

        return null;
    }
}
