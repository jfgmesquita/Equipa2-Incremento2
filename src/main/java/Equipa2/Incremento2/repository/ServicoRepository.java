package Equipa2.Incremento2.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Servico;

/**
 * Repositório para a entidade Servico.
 * Fornece operações de acesso a dados para a entidade Servico.
 */
@Repository
public interface ServicoRepository extends JpaRepository<Servico, UUID> {

    /**
     * Encontra todos os serviços por ID do profissional.
     *
     * @param profissionalId o ID do profissional
     * @return uma lista de serviços associados ao profissional
     */
    public List<Servico> findAllByProfissionalId(UUID profissionalId);

}
