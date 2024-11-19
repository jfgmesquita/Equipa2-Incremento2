package Equipa2.Incremento2.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Equipa2.Incremento2.model.enums.MetodoPagamento;
import Equipa2.Incremento2.model.enums.Servicos;
import Equipa2.Incremento2.model.enums.UserType;

/**
 * A classe Profissional representa um utilizador que é um profissional com especialidade, experiência e valor por hora.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Profissional extends Utilizador {

    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private Servicos especialidade;

    @Column(name = "experiencia")
    private int experiencia;

    @Column(name = "metodo_pagamento")
    @Enumerated(EnumType.STRING)
    private MetodoPagamento formaDePagamento;

    @OneToMany(mappedBy = "profissional")
    private List<Servico> servicos;

    @OneToMany(mappedBy = "profissional")
    private List<Solicitacao> solicitacoes;

    @OneToMany(mappedBy = "profissional")
    private List<Avaliacao> avaliacoes;

    /**
     * Construtor que inicializa um profissional com os dados fornecidos.
     *
     * @param nome o nome do profissional
     * @param email o email do profissional
     * @param password a palavra-passe do profissional
     * @param morada a morada do profissional
     * @param especialidade a especialidade do profissional
     * @param experiencia a experiência do profissional
     * @param formaDePagamento a forma de pagamento do profissional
     */
    public Profissional(String nome, String email, String password, String morada, Servicos especialidade, int experiencia, MetodoPagamento formaDePagamento) {
        super(nome, email, password, morada, UserType.PROFISSIONAL);
        this.especialidade = especialidade;
        this.experiencia = experiencia;
        this.formaDePagamento = formaDePagamento;
        servicos = new ArrayList<>();
        solicitacoes = new ArrayList<>();
        avaliacoes = new ArrayList<>();
    }
    
}
