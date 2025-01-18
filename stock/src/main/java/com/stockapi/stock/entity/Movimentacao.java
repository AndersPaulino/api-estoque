package com.stockapi.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_movimentacao", schema = "public")
public class Movimentacao extends AbstractEntity{
    @OneToOne
    @JoinColumn(name = "cl_produto_id")
    private Produto produto;

    @Column(name = "cl_produto_entrada", nullable = false)
    private int entrada;

    @Column(name = "cl_produto_saida")
    private int saida;

    @Column(name = "cl_total_produto")
    private int totalProduto;

    public Produto getProduto() {
        return produto;
    }

    public int getEntrada() {
        return entrada;
    }

    public int getSaida() {
        return saida;
    }

    public int getTotalProduto() {
        return totalProduto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public void setSaida(int saida) {
        this.saida = saida;
    }

    public void setTotalProduto(int totalProduto) {
        this.totalProduto = totalProduto;
    }
}
