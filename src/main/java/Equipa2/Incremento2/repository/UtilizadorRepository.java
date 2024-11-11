package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Equipa2.Incremento2.model.Utilizador;
@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, UUID> {}
