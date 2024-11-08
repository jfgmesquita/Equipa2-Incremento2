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
	private Cliente origemCliente;
	private Profissional destinoProfissional;
	
	@Enumerated(EnumType.STRING)
	private MetodoPagamento metodo;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;
	
    public Pagamento(double valor, Cliente origemCliente, Profissional destinoProfissional, MetodoPagamento metodo) {
        this.valor = valor;
        this.origemCliente = origemCliente;
        this.destinoProfissional = destinoProfissional;
        this.metodo = metodo;
        status = StatusPagamento.PENDENTE;
        
    }
}