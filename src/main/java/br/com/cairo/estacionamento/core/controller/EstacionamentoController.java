package br.com.cairo.estacionamento.core.controller;

import br.com.cairo.estacionamento.core.models.dto.EstacionamentoDto;
import br.com.cairo.estacionamento.core.service.EstacionamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

    private EstacionamentoService estacionamentoService;

    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.estacionamentoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(this.estacionamentoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody EstacionamentoDto estacionamentoDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.estacionamentoService.cadastrar(estacionamentoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Integer id,
                                    @RequestBody EstacionamentoDto estacionamentoDto) {

        return ResponseEntity.ok(this.estacionamentoService.editar(id, estacionamentoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Integer id) {
        this.estacionamentoService.deletar(id);

        return ResponseEntity.ok().build();
    }
}
