package Equipa2.Incremento2.model;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import Equipa2.Incremento2.model.enums.UserType;

/**
 * Classe que representa um utilizador.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "utilizador")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private String password;

    private String morada;

    @Enumerated(EnumType.STRING)
    private UserType UserType;

    /**
     * Construtor que inicializa um novo utilizador com os dados fornecidos.
     * O ID é gerado automaticamente e aleatoriamente.
     *
     * @param nome     Nome do utilizador.
     * @param email    Email do utilizador.
     * @param password Password do utilizador.
     * @param morada   Morada do utilizador.
     */
    public Utilizador(String nome, String email, String password, String morada, UserType userType) {
       
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.UserType = userType;
    }
}
