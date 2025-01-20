package com.stockapi.stock.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_produto", schema = "public")
public class Produto extends AbstractEntity{

    @Column(name = "cl_nome_produto", unique = true)
    private String nomeProduto;

    @ManyToOne
    @JoinColumn(name = "cl_tipo_id")
    private Tipo tipo;

    @Column(name = "cl_descricao",nullable = false, length = 255)
    private String descricao;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
