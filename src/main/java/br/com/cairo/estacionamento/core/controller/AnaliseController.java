package br.com.cairo.estacionamento.core.controller;

import br.com.cairo.estacionamento.core.service.EstacionamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analise")
public class AnaliseController {
    
    private final EstacionamentoService estacionamentoService;

    public AnaliseController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @GetMapping("/vagas-ocupadas")
    public ResponseEntity<?> relatorioVagasOcupadas(){
        return ResponseEntity.ok(this.estacionamentoService.relatorioVagasOcupadas());
    }
}
