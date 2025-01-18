package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Estoque;
import com.stockapi.stock.entity.Movimentacao;

import java.time.LocalDateTime;
import java.util.List;

public class EstoqueDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private LocalDateTime atualizar;
    private String nomeEstoque;
    private List<Movimentacao> movimentacao;

    public EstoqueDTO(Estoque estoque){
        id = estoque.getId();
        ativo = estoque.isAtivo();
        registro = estoque.getRegistro();
        atualizar = estoque.getAtualizar();
        nomeEstoque = estoque.getNomeEstoque();
        movimentacao = estoque.getMovimentacao();
    }

    public EstoqueDTO(Long id, boolean ativo, LocalDateTime registro, LocalDateTime atualizar, String nomeEstoque, List<Movimentacao> movimentacao){
        this.id = id;
        this.ativo = ativo;
        this.registro = registro;
        this.atualizar = atualizar;
        this.nomeEstoque = nomeEstoque;
        this.movimentacao = movimentacao;
    }

    public Long getId() {
        return id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getRegistro() {
        return registro;
    }

    public LocalDateTime getAtualizar() {
        return atualizar;
    }

    public String getNomeEstoque() {
        return nomeEstoque;
    }

    public List<Movimentacao> getMovimentacao() {
        return movimentacao;
    }
}
