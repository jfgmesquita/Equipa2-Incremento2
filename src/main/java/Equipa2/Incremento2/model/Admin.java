package Equipa2.Incremento2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa um administrador.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends Utilizador {
    private String codigo;

    /**
     * Retorna uma representação em string do utilizador.
     *
     * @return String que representa o utilizador.
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
            "Código de administrador: " + codigo;
    }
}
