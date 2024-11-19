package Equipa2.Incremento2.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import Equipa2.Incremento2.model.enums.MetodoPagamento;
import Equipa2.Incremento2.model.enums.Servicos;
import Equipa2.Incremento2.model.enums.UserType;


/**
 * DTO para o utilizador, usado para transferir dados através da API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilizadorDTO {

    private UUID id;

    private String nome;

    private String email;

    private String password;

    private String morada;

    private UserType userType;  // Define se é Clientem, Profissional ou Admin

    // Para Cliente
    private MetodoPagamento formaDePagamento;

    // Para Profissional
    private Servicos especialidade; 
    private int experiencia;

    // Para Admin
    private String codigo;

    /**
     * Construtor para Cliente.
     * 
     * @param id o ID do utilizador
     * @param nome o nome do utilizador
     * @param email o email do utilizador
     * @param password a password do utilizador
     * @param morada a morada do utilizador
     * @param userType o tipo de utilizador
     * @param formaDePagamento a forma de pagamento do cliente
     * @return um objeto UtilizadorDTO
     */
    public UtilizadorDTO(UUID id, String nome, String email, String password, String morada, UserType userType, MetodoPagamento formaDePagamento){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.userType = userType;
        this.formaDePagamento = formaDePagamento;
    }
    
    /**
     * Construtor para Profissional.
     * 
     * @param id o ID do utilizador
     * @param nome o nome do utilizador
     * @param email o email do utilizador
     * @param password a password do utilizador
     * @param morada a morada do utilizador
     * @param userType o tipo de utilizador
     * @param formaDePagamento a forma de pagamento do utilizador
     * @param especialidade a especialidade do profissional
     * @param experiencia a experiência do profissional
     * @return um objeto UtilizadorDTO
     */
    public UtilizadorDTO(UUID id, String nome, String email, String password, String morada, UserType userType, MetodoPagamento formaDePagamento, Servicos especialidade, int experiencia){
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

    /**
     * Construtor para Admin.
     * 
     * @param id o ID do utilizador
     * @param nome o nome do utilizador
     * @param email o email do utilizador
     * @param password a password do utilizador
     * @param morada a morada do utilizador
     * @param userType o tipo de utilizador
     * @param codigo o código do admin
     * @return um objeto UtilizadorDTO
     */
    public UtilizadorDTO(UUID id, String nome, String email, String password, String morada, UserType userType, String codigo){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.userType = userType;
        this.codigo = codigo;
    }

}
