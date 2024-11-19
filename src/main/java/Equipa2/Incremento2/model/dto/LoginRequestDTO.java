package Equipa2.Incremento2.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para o pedido de login, usado para transferir dados através da API.
 */
@Getter
@Setter
public class LoginRequestDTO {

    private String email;
    private String password;
    
}
