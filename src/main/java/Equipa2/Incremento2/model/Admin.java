package Equipa2.Incremento2.model;

import jakarta.persistence.*;
import Equipa2.Incremento2.model.enums.UserType;

/**
 * Classe que representa um administrador.
 */
@Entity
public class Admin extends Utilizador {
    private String codigo;

    /**
     * Construtor que inicializa um novo administrador com os dados fornecidos.
     * O ID é gerado automaticamente e aleatoriamente.
     *
     * @param nome     Nome do administrador.
     * @param email    Email do administrador.
     * @param password Password do administrador.
     * @param morada   Morada do administrador.
     * @param codigo   Código do administrador.
     */
    public Admin(String nome, String email, String password, String morada, UserType userType, String codigo) {
        super(nome, email, password, morada, userType);
        this.codigo = codigo;
    }
    
    /**
     * Obtém o código do administrador.
     *
     * @return Código do administrador.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define o código do administrador.
     *
     * @param codigo Código do administrador.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna uma representação em string do utilizador.
     *
     * @return String que representa o utilizador.
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
            "Código de administrador: " + codigo;
    }
}
