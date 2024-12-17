package Equipa2.Incremento2.model;

import java.io.Serializable;
import java.util.UUID;

import Equipa2.Incremento2.model.enums.StatusAtivo;
import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Equipa2.Incremento2.model.enums.UserType;

/**
 * Classe que representa um utilizador.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "utilizador")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilizador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private String password;

    private String morada;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private StatusAtivo status;

    /**
     * Construtor que inicializa um novo utilizador com os dados fornecidos.
     * O ID é gerado automaticamente e aleatoriamente.
     *
     * @param nome     Nome do utilizador.
     * @param email    Email do utilizador.
     * @param password Password do utilizador.
     * @param morada   Morada do utilizador.
     * @param userType Tipo de utilizador.
     */
    public Utilizador(String nome, String email, String password, String morada, UserType userType) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.userType = userType;
        this.status = StatusAtivo.ATIVO;
    }
    
}
