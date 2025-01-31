package com.stockapi.stock.service;

import com.stockapi.stock.dto.ProdutoDTO;
import com.stockapi.stock.entity.Produto;
import com.stockapi.stock.entity.Tipo;
import com.stockapi.stock.repository.ProdutoRepository;
import com.stockapi.stock.repository.TipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private TipoRepository tipoRepository;

    private ProdutoService produtoService;

    private Produto produto;

    private Tipo tipo;

    private ProdutoDTO produtoDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        produtoService = new ProdutoService(produtoRepository, tipoRepository);

        tipo = new Tipo();

        produto = new Produto();
        produto.setTipo(tipo);

        produtoDTO = new ProdutoDTO(produto);
    }

    @Test
    void testFindByName(){
        String nome = "Produto1";

        Produto produto1 = new Produto();
        produto1.setTipo(tipo);
        produto1.setNomeProduto(nome);

        when(produtoRepository.findByNomeProduto(nome)).thenReturn(produto1);

        ProdutoDTO produtoDTO1 = produtoService.findByNome(nome);

        assertNotNull(produtoDTO1);

        assertEquals(nome, produtoDTO1.getNomeProduto());
    }
}
