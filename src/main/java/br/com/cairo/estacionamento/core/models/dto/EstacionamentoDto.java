package br.com.cairo.estacionamento.core.models.dto;

import br.com.cairo.estacionamento.core.models.entity.Estacionamento;
import br.com.cairo.estacionamento.core.models.entity.Vaga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstacionamentoDto {
    private Integer id;
    private String nome;
    private Integer totalVagas;

    public Estacionamento toEntity() {

        return Estacionamento.builder()
                .nome(nome)
                .vagas(adicionarVagas(this.totalVagas))
                .build();
    }

    public List<Vaga> adicionarVagas(Integer total) {
        List<Vaga> vagas = new ArrayList<>();

        int i = 1;
        while (i <= total) {
            vagas.add(Vaga.builder()
                    .posicao(i)
                    .status(false)
                    .build());
            i++;
        }

        return vagas;
    }

    public List<Vaga> adicionarVagasAPartirDoTotal(Integer inicio, Integer total) {
        List<Vaga> vagas = new ArrayList<>();

        int i = inicio;
        while (i <= (inicio + total)) {
            vagas.add(Vaga.builder()
                    .posicao(i)
                    .status(false)
                    .build());
            i++;
        }

        return vagas;
    }
}
