package com.stockapi.stock.controller;

import com.stockapi.stock.dto.MovimentacaoDTO;
import com.stockapi.stock.entity.Movimentacao;
import com.stockapi.stock.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;

    @Autowired
    public MovimentacaoController (MovimentacaoService movimentacaoService){
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> findById(@PathVariable Long id){
        return movimentacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>> findAll() {
        List<MovimentacaoDTO> movimentacaoDTO = movimentacaoService.findAll();
        return ResponseEntity.ok(movimentacaoDTO);
    }

    @GetMapping("ativo/{ativo}")
    public ResponseEntity<List<MovimentacaoDTO>> findByAtivo(@PathVariable boolean ativo) {
        try {
            List<MovimentacaoDTO> movimentacaoDTO = movimentacaoService.findByAtivo(ativo);

            if (!movimentacaoDTO.isEmpty()) {
                return ResponseEntity.ok(movimentacaoDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("registro/dia/{registro}")
    public ResponseEntity<List<MovimentacaoDTO>> findByDiaRegistro(@PathVariable("registro") LocalDate registro) {
        try {
            List<MovimentacaoDTO> movimentacaoDTO = movimentacaoService.findByDiaRegistro(registro);

            if (!movimentacaoDTO.isEmpty()) {
                return ResponseEntity.ok(movimentacaoDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("atualizar/dia/{atualizar}")
    public ResponseEntity<List<MovimentacaoDTO>> findByDiaAtualizar(@PathVariable("atualizar") LocalDate atualizar) {
        try {
            List<MovimentacaoDTO> movimentacaoDTOS = movimentacaoService.findByDiaAtualizar(atualizar);

            if (!movimentacaoDTOS.isEmpty()) {
                return ResponseEntity.ok(movimentacaoDTOS);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Movimentacao movimentacao) {
        try {
            movimentacaoService.cadastrar(movimentacao);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Movimentacao movimentacao) {
        try {
            movimentacaoService.atualizar(id, movimentacao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o registro.");
        }
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            movimentacaoService.deletar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro");
        }
    }
}
