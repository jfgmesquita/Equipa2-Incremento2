package Equipa2.Incremento2.model;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Equipa2.Incremento2.model.enums.UserType;

/**
 * Classe que representa um administrador.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Admin extends Utilizador {

    private String codigo;

    /**
     * Construtor que inicializa um administrador com os dados fornecidos.
     *
     * @param nome o nome do administrador
     * @param email o email do administrador
     * @param password a palavra-passe do administrador
     * @param morada a morada do administrador
     * @param userType o tipo de utilizador (administrador)
     * @param codigo o código do administrador
     */
    public Admin(String nome, String email, String password, String morada, UserType userType, String codigo) {
        super(nome, email, password, morada, userType);
        this.codigo = codigo;
    }

}
