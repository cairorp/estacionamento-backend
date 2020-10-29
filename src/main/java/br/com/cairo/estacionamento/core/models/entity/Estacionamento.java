package br.com.cairo.estacionamento.core.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "TOPIC", name = "ESTACIONAMENTO")
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @OneToMany(cascade = ALL, fetch = FetchType.EAGER)
    @JoinTable(schema = "TOPIC", name = "ESTACIONAMENTO_VAGA",
            joinColumns = @JoinColumn(name = "ESTACIONAMENTO_ID"),
            inverseJoinColumns = @JoinColumn(name = "VAGA_ID"))
    private List<Vaga> vagas;
}
