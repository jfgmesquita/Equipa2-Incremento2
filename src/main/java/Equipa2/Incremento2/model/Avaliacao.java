package Equipa2.Incremento2.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A classe Avaliacao representa uma avaliação feita por um cliente sobre um serviço prestado por um profissional.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Avaliacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int valor;
    
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
    
    private String descricao;

    private String data;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_solicitacao", referencedColumnName = "id")
    private Solicitacao servico;
    
    /**
     * Construtor que inicializa uma avaliação com os dados fornecidos.
     *
     * @param valor o valor da avaliação
     * @param descricao a descrição da avaliação
     * @param servico a solicitação de serviço associada à avaliação
     */
    public Avaliacao(int valor, String descricao, Solicitacao servico) {
        this.valor = valor;
        this.descricao = descricao;
        this.servico = servico;
        data = "Hoje";
    }
}