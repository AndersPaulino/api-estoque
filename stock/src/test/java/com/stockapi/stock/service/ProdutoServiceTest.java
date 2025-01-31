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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testFindAll(){
        produto.setTipo(tipo);

        Produto produto1 = new Produto();

        produto1.setTipo(tipo);

        List<Produto> produtos = new ArrayList<>();

        produtos.add(produto1);
        produtos.add(produto);

        when(produtoRepository.findAll()).thenReturn(produtos);

        List<ProdutoDTO> produtoDTOS = produtoService.findAll();

        assertEquals(produtos.size(), produtoDTOS.size());
    }

    @Test
    void testValidarProduto() {
        produto.setTipo(tipo);
        produto.setNomeProduto(null);

        Produto produto1 = new Produto();
        produto1.setTipo(tipo);
        produto1.setNomeProduto("Nome Inválido!@#");

        Produto produto2 = new Produto();
        produto2.setTipo(tipo);
        produto2.setNomeProduto("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            produtoService.validarProduto(produto);
        });

        assertThat(exception.getMessage()).isEqualTo("Nome De Produto Não Preenchido");

        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, ()->{
            produtoService.validarProduto(produto1);
        });

        assertThat(exception1.getMessage()).isEqualTo("Nome De Produto Invalido");

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, ()->{
            produtoService.validarProduto(produto2);
        });

        assertThat(exception2.getMessage()).isEqualTo("Nome De Produto Não Preenchido");
    }

    @Test
    void testFindByAtivo(){
        List<Produto> produtos = new ArrayList<>();

        Produto produto1 = new Produto();

        produto1.setTipo(tipo);
        produto.setTipo(tipo);

        produtos.add(produto1);
        produtos.add(produto);

        when(produtoRepository.findByAtivo(true)).thenReturn(produtos);

        List<ProdutoDTO> produtoDTOS = produtoService.findByAtivo(true);

        assertEquals(produtoDTOS.size(),produtos.size());
    }
}
