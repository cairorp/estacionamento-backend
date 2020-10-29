package br.com.cairo.estacionamento.core.service.impl;

import br.com.cairo.estacionamento.core.exception.VagaException;
import br.com.cairo.estacionamento.core.repository.VagaRepository;
import br.com.cairo.estacionamento.core.service.VagaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaServiceImpl implements VagaService {

    private VagaRepository vagaRepository;

    public VagaServiceImpl(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    @Override
    public void alterarStatus(Integer id, Boolean status) {
        try {
            this.vagaRepository.atualizarStatus(id, status);
        } catch (Exception ex) {
            throw new VagaException("Ocorreu um erro ao tentar atualizar o status da vaga.", ex);
        }
    }

    @Override
    public void deletarVagasNaoUsadas() {
        this.vagaRepository.deletarVagasNaoUsadas();
    }


}
