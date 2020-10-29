package br.com.cairo.estacionamento.core.service;

public interface VagaService {
    void alterarStatus(Integer id, Boolean status);

    void deletarVagasNaoUsadas();
}
