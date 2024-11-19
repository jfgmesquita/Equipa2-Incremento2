package Equipa2.Incremento2.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Equipa2.Incremento2.model.enums.Servicos;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Servicos tipo;

    private String descricao;

    private LocalDateTime data;

    private double valorHora;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
}
