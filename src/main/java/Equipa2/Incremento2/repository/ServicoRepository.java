package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Servico;
@Repository
public interface ServicoRepository extends JpaRepository<Servico, UUID> {}
