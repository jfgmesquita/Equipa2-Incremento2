package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Equipa2.Incremento2.model.Cliente;

/**
 * Repositório para a entidade Cliente.
 * Fornece operações de acesso a dados para a entidade Cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {}
