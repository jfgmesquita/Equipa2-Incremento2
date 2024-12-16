package Equipa2.Incremento2.model.dto;

import Equipa2.Incremento2.model.Cliente;
import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double valor;

    private UUID solicitacaoId;

    private UUID clienteId;

    private UUID profissionalId;

    private StatusPagamento status;

    private int horas;
}
