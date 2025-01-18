package com.stockapi.stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_movimentacao", schema = "public")
public class Movimentacao extends AbstractEntity{
    @OneToOne
    @JoinColumn(name = "cl_produto_id")
    private Produto produto;

    @Column(name = "cl_produto_entrada", nullable = false)
    private Integer entrada;

    @Column(name = "cl_produto_saida")
    private Integer saida;

    @Column(name = "cl_total_produto")
    private int totalProduto;

    public Produto getProduto() {
        return produto;
    }

    public Integer getEntrada() {
        return entrada;
    }

    public Integer getSaida() {
        return saida;
    }

    public Integer getTotalProduto() {
        return totalProduto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setEntrada(Integer entrada) {
        this.entrada = entrada;
    }

    public void setSaida(Integer saida) {
        this.saida = saida;
    }

    public void setTotalProduto(Integer totalProduto) {
        this.totalProduto = totalProduto;
    }
}
