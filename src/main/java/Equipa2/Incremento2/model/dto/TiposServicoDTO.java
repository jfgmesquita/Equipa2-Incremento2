package Equipa2.Incremento2.model.dto;

import Equipa2.Incremento2.model.enums.StatusAtivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TiposServicoDTO {

    private Integer id;

    private LocalDateTime dataCreated;

    private String nome;

    private StatusAtivo status;

    private UUID adminId;

    private List<ServicoDTO> servicos;

}
