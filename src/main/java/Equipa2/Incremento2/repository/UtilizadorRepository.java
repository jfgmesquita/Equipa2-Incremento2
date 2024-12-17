package Equipa2.Incremento2.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    public Utilizador findByEmail(String email);

     /**
     * Encontra todos os utilizadores por tipo.
     *
     * @param tipo o tipo de utilizador
     * @return uma lista de utilizadores associados ao tipo
     */
    @Query(value = "SELECT *, 0 AS clazz_, 0 AS codigo, 0 AS forma_de_pagamento, 0 AS especialidade, 0 AS experiencia, 0 AS metodo_pagamento FROM utilizador u WHERE u.user_type = ?1", nativeQuery = true)
    public List<Utilizador> findAllByUserType(String tipo);
    
}
