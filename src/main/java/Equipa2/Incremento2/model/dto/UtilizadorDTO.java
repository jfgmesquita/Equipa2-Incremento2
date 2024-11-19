package Equipa2.Incremento2.model.dto;

import Equipa2.Incremento2.model.enums.UserType;
import Equipa2.Incremento2.model.enums.MetodoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO para o utilizador, usado para transferir dados através da API.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilizadorDTO {

    private UUID id;
    private String nome;
    private String email;
    private String password;
    private String morada;
    private UserType userType;  // Define se é Profissional, Cliente ou Admin

    //Construtor para cliente
    public UtilizadorDTO(UUID id, String nome, String email, String password, String morada, UserType userType, MetodoPagamento formaDePagamento){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.userType = userType;
        this.formaDePagamento = formaDePagamento;
    }

    //Construtor para profissional
    public UtilizadorDTO(UUID id, String nome, String email, String password, String morada, UserType userType, MetodoPagamento formaDePagamento, String especialidade, int experiencia){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.userType = userType;
        this.formaDePagamento = formaDePagamento;
        this.especialidade = especialidade;
        this.experiencia = experiencia;
    }

    //Construtor para Admin
    public UtilizadorDTO(UUID id, String nome, String email, String password, String morada, UserType userType, String codigo){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.userType = userType;
        this.codigo = codigo;
    }

    // Para Profissional
    private String especialidade;
    private int experiencia;

    // Para Cliente
    private MetodoPagamento formaDePagamento;

    // Para Admin
    private String codigo;
}
