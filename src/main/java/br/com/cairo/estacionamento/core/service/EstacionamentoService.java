package br.com.cairo.estacionamento.core.service;

import br.com.cairo.estacionamento.core.models.dto.EstacionamentoDto;
import br.com.cairo.estacionamento.core.models.dto.RelatorioVagasOcupadasDto;
import br.com.cairo.estacionamento.core.models.entity.Estacionamento;

import java.util.List;

public interface EstacionamentoService {
    EstacionamentoDto cadastrar(EstacionamentoDto estacionamentoDto);

    EstacionamentoDto editar(Integer id, EstacionamentoDto estacionamentoDto);

    void deletar(Integer id);

    List<EstacionamentoDto> listarTodos();

    Estacionamento buscarPorId(Integer id);

    List<RelatorioVagasOcupadasDto> relatorioVagasOcupadas();
}
