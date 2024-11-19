package Equipa2.Incremento2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Equipa2.Incremento2.model.Utilizador;

/**
 * Repositório para a entidade Utilizador.
 * Fornece operações de acesso a dados para a entidade Utilizador.
 */
@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, UUID> {

    /**
     * Encontra um utilizador pelo email.
     *
     * @param email o email do utilizador
     * @return o utilizador com o email fornecido
     */
    Utilizador findByEmail(String email);
    
}
