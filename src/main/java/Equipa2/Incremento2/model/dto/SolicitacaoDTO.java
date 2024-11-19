package Equipa2.Incremento2.model.dto;

import Equipa2.Incremento2.model.enums.StatusServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoDTO {
    private UUID id;
    private StatusServico status;
    private UtilizadorDTO cliente;
    private ServicoDTO servico;
    private String data;
}
