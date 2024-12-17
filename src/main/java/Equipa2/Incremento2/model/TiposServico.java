package Equipa2.Incremento2.model;

import Equipa2.Incremento2.model.enums.StatusAtivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tipos_servico")
public class TiposServico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusAtivo status;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "tipoServico")
    private List<Servico> servicos;

    @Column(name = "data_created")
    private LocalDateTime dataCreated;

    public TiposServico(){
        dataCreated = LocalDateTime.now();
        status = StatusAtivo.ATIVO;
        servicos = new ArrayList<>();
    }
}
