package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Solicitacao;
@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, UUID> {}