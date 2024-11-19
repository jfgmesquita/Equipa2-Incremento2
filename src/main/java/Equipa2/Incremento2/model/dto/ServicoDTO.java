package Equipa2.Incremento2.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {

    private UUID id;
    private String titulo;

    private String descricao;
    private String data;
    private double valorHora;

    private UtilizadorDTO profissional;
}
