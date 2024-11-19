package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Equipa2.Incremento2.model.Profissional;

/**
 * Repositório para a entidade Profissional.
 * Fornece operações de acesso a dados para a entidade Profissional.
 */
public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {}
