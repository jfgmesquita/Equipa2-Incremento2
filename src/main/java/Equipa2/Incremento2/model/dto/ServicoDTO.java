package Equipa2.Incremento2.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import Equipa2.Incremento2.model.enums.Servicos;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServicoDTO {

    private UUID id;
    
    private Servicos tipo;

    private String descricao;

    private String data;

    private double valorHora;

    private UtilizadorDTO profissional;
}
