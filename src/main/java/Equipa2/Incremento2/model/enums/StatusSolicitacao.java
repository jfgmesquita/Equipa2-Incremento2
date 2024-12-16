package Equipa2.Incremento2.model.enums;

/**
 * A enumeração StatusServico representa os possíveis estados de um serviço.
 */
public enum StatusSolicitacao {
    /**
     * Indica que o serviço está pendente.
     */
    PENDENTE,

    /**
     * Indica que o serviço está em andamento.
     */
    ANDAMENTO,

    /**
     * Indica que o serviço foi aceite.
     */
    ACEITE,

    /**
     * Indica que o serviço foi concluído mas o pagamento está pendente
     */
    PAGAMENTO_PENDENTE,

    /**
     * Indica que o serviço foi concluído.
     */
    CONCLUIDO,

    /**
     * Indica que o serviço foi cancelado.
     */
    CANCELADO
}
