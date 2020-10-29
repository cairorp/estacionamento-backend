package br.com.cairo.estacionamento.core.repository;

import br.com.cairo.estacionamento.core.models.dto.EstacionamentoDto;
import br.com.cairo.estacionamento.core.models.dto.RelatorioVagasOcupadasDto;
import br.com.cairo.estacionamento.core.models.entity.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer> {

    @Query("SELECT new br.com.cairo.estacionamento.core.models.dto.EstacionamentoDto(e.id, e.nome, size(e.vagas))" +
            " FROM Estacionamento e")
    List<EstacionamentoDto> buscarTodos();

    @Query("SELECT new br.com.cairo.estacionamento.core.models.dto.RelatorioVagasOcupadasDto(e.nome, count(v.status))" +
            "FROM Estacionamento e " +
            "JOIN e.vagas v " +
            "WHERE v.status = true " +
            "GROUP BY e.nome")
    List<RelatorioVagasOcupadasDto> relatorioVagasOcupadas();
}
