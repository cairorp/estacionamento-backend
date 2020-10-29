package br.com.cairo.estacionamento.core.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVagasOcupadasDto {
    private String nome;
    private Long total;
}
