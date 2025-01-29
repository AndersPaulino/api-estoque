package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Produto;
import com.stockapi.stock.entity.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PrivilegedAction;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoDTOTest {

    private Produto produto;
    private ProdutoDTO produtoDTO;
    private Tipo tipo;

    @BeforeEach
    void setUp(){
        produto = new Produto();
        tipo = new Tipo();

        tipo.setNomeTipo("nome");
        produto.setTipo(tipo);

        produto.setDescricao("teste");
        produto.setNomeProduto("nome teste");

        produtoDTO = new ProdutoDTO(produto);
    }

    @Test
    void testConstructorWithProduto(){
        assertEquals(produto.getId(), produtoDTO.getId());
        assertEquals(produto.getNomeProduto(), produtoDTO.getNomeProduto());
        assertEquals(produto.getRegistro(), produtoDTO.getRegistro());
        assertEquals(produto.getAtualizar(), produtoDTO.getAtualizar());
        assertEquals(produto.getDescricao(), produtoDTO.getDescricao());
    }

    @Test
    void testCOnstructorWithIndividualParameters(){
        ProdutoDTO produtoDTO1 = new ProdutoDTO(1L, true, LocalDateTime.now(), LocalDateTime.now(), "nome", "descricao", tipo);

        assertEquals(1L, produtoDTO1.getId());
        assertEquals(true, produtoDTO1.isAtivo());
        assertEquals("nome", produtoDTO1.getNomeProduto());
        assertEquals("descricao", produtoDTO1.getDescricao());
    }
}
