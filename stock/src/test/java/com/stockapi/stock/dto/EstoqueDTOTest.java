package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Estoque;
import com.stockapi.stock.entity.Movimentacao;
import com.stockapi.stock.entity.Produto;
import com.stockapi.stock.entity.Tipo;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class EstoqueDTOTest {

    private Tipo tipo;
    private Produto produto;
    private List<Movimentacao> movimentacao;
    private Estoque estoque;
    private EstoqueDTO estoqueDTO;

    @BeforeEach
    void setUp(){
        tipo = new Tipo();
        tipo.setNomeTipo("Tipo");

        produto = new Produto();
        produto.setNomeProduto("Produto");
        produto.setTipo(tipo);

        movimentacao = new ArrayList<>();
        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        movimentacao.add(mov);

        estoque = new Estoque();
        estoque.setMovimentacao(movimentacao);
    }
}
