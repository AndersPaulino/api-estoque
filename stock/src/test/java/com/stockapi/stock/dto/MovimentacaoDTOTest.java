package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Movimentacao;
import com.stockapi.stock.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovimentacaoDTOTest {

    private Movimentacao movimentacao;
    private MovimentacaoDTO movimentacaoDTO;
    private Produto produto;

    @BeforeEach
    void setUp(){
        movimentacao = new Movimentacao();
        produto = new Produto();

        produto.setNomeProduto("nome");

        movimentacao.setProduto(produto);
        movimentacao.setEntrada(50);
        movimentacao.setSaida(15);
        movimentacao.setTotalProduto(movimentacao.getEntrada() - movimentacao.getSaida());

        movimentacaoDTO = new MovimentacaoDTO(movimentacao);
    }

    @Test
    void testConstructorWithMovimentacao(){
        assertEquals(movimentacao.getId(), movimentacaoDTO.getId());
        assertEquals(movimentacao.getRegistro(), movimentacaoDTO.getRegistro());
        assertEquals(movimentacao.getAtualizar(), movimentacaoDTO.getAtualizar());
        assertEquals(movimentacao.getTotalProduto(), movimentacaoDTO.getTotalProduto());
        assertEquals(movimentacao.getProduto(), movimentacaoDTO.getProduto());
    }

    @Test
    void tesstConstructorWithIndividualParameters(){
        MovimentacaoDTO movimentacaoDTO1 = new MovimentacaoDTO(1L,true, LocalDateTime.now(), LocalDateTime.now(), produto, movimentacao.getEntrada()- movimentacao.getSaida());

        assertEquals(1L, movimentacaoDTO1.getId());
        assertEquals(produto, movimentacaoDTO1.getProduto());
        assertEquals(movimentacao.getTotalProduto(), movimentacaoDTO1.getTotalProduto());
        assertEquals(true, movimentacaoDTO1.isAtivo());
    }
}
