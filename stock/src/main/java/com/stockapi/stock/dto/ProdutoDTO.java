package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Produto;
import com.stockapi.stock.entity.Tipo;

import java.time.LocalDateTime;


public class ProdutoDTO {

    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private LocalDateTime atualizar;
    private String nomeProduto;
    private String descricao;
    private Long tipo;

    public ProdutoDTO(Produto produto){
        id = produto.getId();
        ativo = produto.isAtivo();
        registro = produto.getRegistro();
        atualizar = produto.getAtualizar();
        nomeProduto = produto.getNomeProduto();
        descricao = produto.getDescricao();
        tipo = produto.getTipo().getId();
    }

    public ProdutoDTO(Long id, boolean ativo, LocalDateTime registro, LocalDateTime atualizar, String nomeProduto, String descricao, Tipo tipo){
        this.id = id;
        this.ativo = ativo;
        this.registro = registro;
        this.atualizar = atualizar;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.tipo = tipo.getId();
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

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getTipo() {
        return tipo;
    }
}
