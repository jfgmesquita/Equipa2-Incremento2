package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Avaliacao;

/**
 * Repositório para a entidade Avaliacao.
 * Fornece operações de acesso a dados para a entidade Avaliacao.
 */
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {}
