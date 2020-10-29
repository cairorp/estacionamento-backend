package br.com.cairo.estacionamento.core.service.impl;

import br.com.cairo.estacionamento.core.exception.EstacionamentoException;
import br.com.cairo.estacionamento.core.models.dto.EstacionamentoDto;
import br.com.cairo.estacionamento.core.models.dto.RelatorioVagasOcupadasDto;
import br.com.cairo.estacionamento.core.models.entity.Estacionamento;
import br.com.cairo.estacionamento.core.repository.EstacionamentoRepository;
import br.com.cairo.estacionamento.core.service.EstacionamentoService;
import br.com.cairo.estacionamento.core.service.VagaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private EstacionamentoRepository estacionamentoRepository;
    private VagaService vagaService;

    public EstacionamentoServiceImpl(EstacionamentoRepository estacionamentoRepository,
                                     VagaService vagaService) {
        this.estacionamentoRepository = estacionamentoRepository;
        this.vagaService = vagaService;
    }

    @Override
    public EstacionamentoDto cadastrar(EstacionamentoDto estacionamentoDto) {
        try {
            Estacionamento estacionamento = this.estacionamentoRepository.save(estacionamentoDto.toEntity());
            return new EstacionamentoDto(estacionamento.getId(), estacionamento.getNome(), estacionamento.getVagas().size());
        } catch (Exception ex) {
            throw new EstacionamentoException("Ocorreu um erro ao tentar cadastrar o estacionamento.", ex);
        }
    }

    @Override
    @Transactional
    public EstacionamentoDto editar(Integer id, EstacionamentoDto estacionamentoDto) {
        Estacionamento estacionamento = buscarPorId(id);
        try {
            montarEstacionamentoEditado(estacionamentoDto,
                    estacionamento);

            this.estacionamentoRepository.save(estacionamento);

            this.vagaService.deletarVagasNaoUsadas();

            return new EstacionamentoDto(estacionamento.getId(),
                    estacionamento.getNome(),
                    estacionamento.getVagas().size());
        } catch (Exception ex) {
            throw new EstacionamentoException("Ocorreu um erro ao tentar editar o estacionamento : " + estacionamento.getNome(), ex);
        }
    }

    public Estacionamento buscarPorId(Integer id) {
        return this.estacionamentoRepository.findById(id)
                .orElseThrow(() ->
                        new EstacionamentoException("Não foi possivel encontrar o estacionamento para o ID"));
    }

    @Override
    public List<RelatorioVagasOcupadasDto> relatorioVagasOcupadas() {
        return this.estacionamentoRepository.relatorioVagasOcupadas();
    }

    @Override
    public void deletar(Integer id) {
        Estacionamento estacionamento = buscarPorId(id);

        try {
            this.estacionamentoRepository.delete(estacionamento);
        } catch (Exception ex) {
            throw new EstacionamentoException("Ocorreu um erro ao tentar deletar o estacionamento " + estacionamento.getNome(), ex);
        }
    }

    @Override
    public List<EstacionamentoDto> listarTodos() {
        return this.estacionamentoRepository.buscarTodos();
    }

    private void montarEstacionamentoEditado(EstacionamentoDto estacionamentoDto,
                                             Estacionamento estacionamento) {

        estacionamento.setNome(estacionamentoDto.getNome());

        if (estacionamentoDto.getTotalVagas() < estacionamento.getVagas().size()) {

            estacionamento.setVagas(estacionamento.getVagas()
                    .stream().filter(vaga ->
                            vaga.getPosicao() <= estacionamentoDto.getTotalVagas()
                    ).collect(toList()));

        } else if (estacionamentoDto.getTotalVagas() > estacionamento.getVagas().size()) {
            estacionamento.getVagas().addAll(estacionamentoDto
                    .adicionarVagasAPartirDoTotal(estacionamento.getVagas().size(),
                            estacionamentoDto.getTotalVagas() - estacionamento.getVagas().size()));
        }
    }
}
