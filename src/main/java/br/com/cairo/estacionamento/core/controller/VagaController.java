package br.com.cairo.estacionamento.core.controller;

import br.com.cairo.estacionamento.core.service.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<?> alterarStatus(@PathVariable("id") Integer id,
                                           @PathVariable("status") Boolean status) {

        this.vagaService.alterarStatus(id, status);

        return ResponseEntity.ok().build();
    }
}
