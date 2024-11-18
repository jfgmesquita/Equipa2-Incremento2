package Equipa2.Incremento2.model.dto;

import Equipa2.Incremento2.model.enums.UserType;
import Equipa2.Incremento2.model.enums.MetodoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para o utilizador, usado para transferir dados através da API.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilizadorDTO {

    private String nome;
    private String email;
    private String password;
    private String morada;
    private UserType userType;  // Define se é Profissional, Cliente ou Admin

    // Para Profissional
    private String especialidade;
    private int experiencia;

    // Para Cliente
    private MetodoPagamento formaDePagamento;

    // Para Admin
    private String codigo;
}
