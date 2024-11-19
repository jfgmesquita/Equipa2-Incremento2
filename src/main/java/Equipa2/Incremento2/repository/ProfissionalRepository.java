package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Profissional;

/**
 * Repositório para a entidade Profissional.
 * Fornece operações de acesso a dados para a entidade Profissional.
 */
@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {}
