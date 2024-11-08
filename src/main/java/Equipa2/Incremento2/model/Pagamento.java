package Equipa2.Incremento2.model;

import Equipa2.Incremento2.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double valor;
	private String origem;
	private String destino;
	
	@Enumerated(EnumType.STRING)
	private MetodoPagamento metodo;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;
	
    public Pagamento(double valor, Cliente origem, Profissional destino, MetodoPagamento metodo) {
        this.valor = valor;
        this.origem = origem.getNome();
        this.destino = destino.getNome();
        this.metodo = metodo;
        status = StatusPagamento.PENDENTE;
        
    }
}