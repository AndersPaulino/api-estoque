package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Estoque;
import com.stockapi.stock.entity.Movimentacao;
import com.stockapi.stock.entity.Produto;
import com.stockapi.stock.entity.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        estoque.setNomeEstoque("Estoque");
        estoqueDTO = new EstoqueDTO(estoque);
    }

    @Test
    void testConstructorWithEstoque(){
        assertEquals(estoque.getId(), estoqueDTO.getId());
        assertEquals(estoque.getNomeEstoque(), estoqueDTO.getNomeEstoque());
        assertEquals(estoque.getMovimentacao(), estoqueDTO.getMovimentacao());
        assertEquals(estoque.getAtualizar(), estoqueDTO.getAtualizar());
        assertEquals(estoque.getRegistro(), estoqueDTO.getRegistro());
        assertEquals(estoque.isAtivo(), estoqueDTO.isAtivo());
    }

    @Test
    void testConstructorWithIndividualParameters(){
        EstoqueDTO estoqueDTO1 = new EstoqueDTO(1L, true, LocalDateTime.now(), LocalDateTime.now(), "Estoque",movimentacao);

        assertEquals(1L, estoqueDTO1.getId());
        assertEquals("Estoque", estoqueDTO1.getNomeEstoque());
        assertEquals(movimentacao, estoqueDTO1.getMovimentacao());
        assertEquals(true, estoqueDTO1.isAtivo());
    }
}
