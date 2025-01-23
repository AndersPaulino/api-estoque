package com.stockapi.stock.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
    }

    @Test
    void testPrePersist(){
        produto.prePersist();
        assertNotNull(produto.getRegistro(), "Registro não deve ser nulo após prePersist");
        assertTrue(produto.isAtivo(), "Ativo deve ser true após prePersist");
    }

    @Test
    void testSettersAndGetters() {
        produto.setAtivo(false);
        produto.setNomeProduto("nome teste");
        produto.setDescricao("nova descricao");
        produto.setRegistro(LocalDateTime.now().minusDays(1));
        produto.setAtualizar(LocalDateTime.now());

        assertFalse(produto.isAtivo(), "Ativo deve ser false após setter");
        assertEquals(produto.getRegistro().toLocalDate(), LocalDateTime.now().minusDays(1).toLocalDate(), "A data de registro não corresponde");
        assertNotNull(produto.getAtualizar(),  "O campo 'atualizar' não deve ser nulo");
        assertEquals("nome teste", produto.getNomeProduto(), "O nome do produto não foi definido corretamente");
    }
}
