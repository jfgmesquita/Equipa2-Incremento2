package Equipa2.Incremento2.model.dto;

import java.util.UUID;

import Equipa2.Incremento2.model.TiposServico;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para o serviço, usado para transferir dados através da API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServicoDTO {

    private UUID id;
    
    private TiposServico tipo;

    private String descricao;

    private String data;

    private double valorHora;

    private UtilizadorDTO profissional;

}
