package Equipa2.Incremento2.model.dto;

import java.util.UUID;

import Equipa2.Incremento2.model.enums.StatusSolicitacao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para a solicitação, usado para transferir dados através da API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitacaoDTO {

    private UUID id;

    private StatusSolicitacao status;

    private UtilizadorDTO cliente;

    private ServicoDTO servico;

    private PagamentoDTO pagamento;

    private String data;


}
