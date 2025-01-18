package com.stockapi.stock.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_estoque", schema = "public")
public class Estoque extends AbstractEntity{
    @Column(name = "cl_nome_estoque", unique = true)
    private String nomeEstoque;

    @ManyToMany
    @JoinTable(name = "tb_estoque_movimentacao",
            joinColumns = @JoinColumn(name = "cl_estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "cl_movimentacao_id")
    )
    private List<Movimentacao> movimentacao = new ArrayList<>();

    public String getNomeEstoque() {
        return nomeEstoque;
    }

    public List<Movimentacao> getMovimentacao() {
        return movimentacao;
    }

    public void setNomeEstoque(String nomeEstoque) {
        this.nomeEstoque = nomeEstoque;
    }

    public void setMovimentacao(List<Movimentacao> movimentacao) {
        this.movimentacao = movimentacao;
    }
}
