package Equipa2.Incremento2.repository;

import Equipa2.Incremento2.model.TiposServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposServicoRespository extends JpaRepository<TiposServico, Integer> {
    public TiposServico findByNome(String nome);
}
