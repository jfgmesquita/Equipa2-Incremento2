package Equipa2.Incremento2.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import Equipa2.Incremento2.model.enums.StatusServico;

/**
 * DTO para a solicitação, usado para transferir dados através da API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitacaoDTO {

    private UUID id;

    private StatusServico status;

    private UtilizadorDTO cliente;

    private ServicoDTO servico;

    private String data;


}
