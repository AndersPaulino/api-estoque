package com.stockapi.stock.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MovimentacaoTest {

    private Movimentacao movimentacao;
    private Produto produto;

    @BeforeEach
    void setUp() {
        movimentacao = new Movimentacao();
        produto = new Produto();
        produto.setNomeProduto("nome");
    }

    @Test
    void testPrePersist() {
        movimentacao.prePersist();
        assertNotNull(movimentacao.getRegistro(), "Registro não deve ser nulo");
        assertTrue(movimentacao.isAtivo(), "Ativo deve ser true após prePersist");
    }

    @Test
    void testPreUpdate() {
        LocalDateTime predate = movimentacao.getAtualizar();
        movimentacao.preUpdate();
        assertNotNull(movimentacao.getAtualizar(), "Atualizar não deve ser nulo após preUpdate");
        assertNotEquals(predate, movimentacao.getAtualizar(), "O valor de 'atualizar'  deve ser diferente após preUpdate");
    }

    @Test
    void testSettersAndGetters() {
        movimentacao.setAtivo(false);
        movimentacao.setRegistro(LocalDateTime.now().minusDays(1));
        movimentacao.setAtualizar(LocalDateTime.now());
        movimentacao.setEntrada(50);
        movimentacao.setSaida(15);
        movimentacao.setTotalProduto(movimentacao.getEntrada() - movimentacao.getSaida());
        movimentacao.setProduto(produto);

        assertFalse(movimentacao.isAtivo(), "Ativo deve ser false após setter");
        assertEquals(movimentacao.getRegistro().toLocalDate(), LocalDateTime.now().minusDays(1).toLocalDate(), "A data de registro não corresponde");
        assertNotNull(movimentacao.getAtualizar(), "O campo 'atualizar' não deve ser nulo");
        assertEquals(50 , movimentacao.getEntrada(), "O numero de entrada não foi definido");
        assertEquals(15 , movimentacao.getSaida(), "O numero de saida não foi definido");
        assertEquals(35 , movimentacao.getTotalProduto(), "O numero totalProduto não foi definido");
        assertEquals( "nome" , movimentacao.getProduto().getNomeProduto(), "O nome não foi definido");
    }
}
