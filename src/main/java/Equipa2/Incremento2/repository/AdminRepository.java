package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Admin;

/**
 * Repositório para a entidade Admin.
 * Fornece operações de acesso a dados para a entidade Admin.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {}
