package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Movimentacao;
import com.stockapi.stock.entity.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentacaoDTO {

    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private LocalDateTime atualizar;
    private Integer totalProduto;
    private Produto produto;
    private Integer entrada;
    private Integer saida;

    public MovimentacaoDTO(Movimentacao movimentacao){
        id = movimentacao.getId();
        ativo = movimentacao.isAtivo();
        registro = movimentacao.getRegistro();
        atualizar = movimentacao.getAtualizar();
        entrada = movimentacao.getEntrada();
        saida = movimentacao.getSaida();
        totalProduto = movimentacao.getTotalProduto();
    }

    public MovimentacaoDTO(Long id, boolean ativo, LocalDateTime registro, LocalDateTime atualizar, Integer entrada, Integer saida, Integer totalProduto){
        this.id = id;
        this.ativo = ativo;
        this.registro = registro;
        this.atualizar = atualizar;
        this.entrada = entrada;
        this.saida = saida;
        this.totalProduto = totalProduto;
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

    public Produto getProduto() {
        return produto;
    }

    public Integer getTotalProduto() {
        return totalProduto;
    }

    public Integer getEntrada() {
        return entrada;
    }

    public Integer getSaida() {
        return saida;
    }
}
