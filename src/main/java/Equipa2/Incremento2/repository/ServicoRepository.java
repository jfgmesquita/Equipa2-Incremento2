package Equipa2.Incremento2.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Servico;
@Repository
public interface ServicoRepository extends JpaRepository<Servico, UUID> {
    public List<Servico> findAllByProfissionalId(UUID profissionalId);
}
