package Equipa2.Incremento2.repository;

import Equipa2.Incremento2.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfissionalRepository extends JpaRepository<Profissional, UUID>
{
}
