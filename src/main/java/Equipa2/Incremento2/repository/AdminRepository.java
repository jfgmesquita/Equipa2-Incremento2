package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Equipa2.Incremento2.model.Admin;

/**
 * Repositório para a entidade Admin.
 * Fornece operações de acesso a dados para a entidade Admin.
 */
public interface AdminRepository extends JpaRepository<Admin, UUID> {}
