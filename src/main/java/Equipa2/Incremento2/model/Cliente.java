package Equipa2.Incremento2.model;

import java.util.List;

import Equipa2.Incremento2.model.enums.*;

import java.util.ArrayList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A classe Cliente representa um utilizador que é um cliente com uma forma de pagamento e uma lista de solicitações.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Cliente")
public class Cliente extends Utilizador {
	
    @OneToMany(mappedBy = "cliente")
    private List<Solicitacao> solicitacoes;

    @Column(name = "forma_de_pagamento")
    @Enumerated(EnumType.STRING)
    private MetodoPagamento formaDePagamento;
    
    /**
     * Construtor que inicializa um cliente com os dados fornecidos.
     *
     * @param nome o nome do cliente
     * @param email o email do cliente
     * @param password a palavra-passe do cliente
     * @param morada a morada do cliente
     * @param formaDePagamento a forma de pagamento do cliente
     */
    public Cliente(String nome, String email, String password, String morada, UserType userType, MetodoPagamento formaDePagamento) {
        super(nome, email, password, morada, userType);
        this.formaDePagamento = formaDePagamento;
        solicitacoes = new ArrayList<Solicitacao>();
    }
}