package com.stockapi.stock.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EstoqueTest {

    private Estoque estoque;
    private List<Movimentacao> movimentacao = new ArrayList<>();
    private Produto produto;

    @BeforeEach
    void setUp(){
        estoque = new Estoque();
        movimentacao = new ArrayList<>();
        produto = new Produto();
        produto.setNomeProduto("nome");

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        movimentacao.add(mov);

        estoque.setMovimentacao(movimentacao);

    }

    @Test
    void testPrePersist(){
        estoque.prePersist();
        assertNotNull(estoque.getRegistro(), "Registro não deve ser nulo após o prePersist");
        assertTrue(estoque.isAtivo(), "Ativo deve ser true após prePersist");
    }

    @Test
    void testPreUpdate() {
        LocalDateTime predate = estoque.getAtualizar();
        estoque.preUpdate();
        assertNotNull(estoque.getAtualizar(),  "Atualizar mão deve ser nulo após o preUpdate");
        assertNotEquals(predate, estoque.getAtualizar(), "O valor de 'atualziar' deve ser diferente após preUpdate");
    }

    @Test
    void testSetterAndGetters() {
        estoque.setAtivo(false);
        estoque.setRegistro(LocalDateTime.now().minusDays(1));
        estoque.setAtualizar(LocalDateTime.now());
        estoque.setNomeEstoque("Estoque Teste");
        estoque.setMovimentacao(movimentacao);

        assertFalse(estoque.isAtivo(), "Ativo deve ser false após o setter");
        assertEquals(estoque.getRegistro().toLocalDate(), LocalDateTime.now().minusDays(1).toLocalDate(), "A data de registro não corresponde");
        assertNotNull(estoque.getAtualizar(), "O campo 'atualizar' nãodeve ser nulo");
        assertEquals("Estoque Teste", estoque.getNomeEstoque(), "O nome do estoque não foi definido corretamente");
        assertEquals(produto , estoque.getMovimentacao().get(0).getProduto(), "A movimentacao nao foi definida corretamente");
    }
}
