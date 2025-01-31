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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        tipo.setNomeTipo("Tipo");

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

    @Test
    void testFindByDiaRegistro(){
        List<Produto> produtos = new ArrayList<>();
        Produto produto1 = new Produto();

        produto1.setTipo(tipo);
        produto.setTipo(tipo);

        produtos.add(produto1);
        produtos.add(produto);

        when(produtoRepository.findByDiaRegistro(LocalDate.now())).thenReturn(produtos);

        List<ProdutoDTO> produtoDTOS = produtoService.findByDiaRegistro(LocalDate.now());

        assertEquals(produtos.size(),produtoDTOS.size());
    }

    @Test
    void testFindByDiaAtualizar(){
        List<Produto> produtos = new ArrayList<>();
        Produto produto1 = new Produto();

        produto1.setTipo(tipo);
        produto.setTipo(tipo);

        produto.setAtualizar(LocalDateTime.of(2012,12,30,0,0,0));
        produto1.setAtualizar(LocalDateTime.now());

        produtos.add(produto1);
        produtos.add(produto);

        when(produtoRepository.findByDiaAtualizar(LocalDate.now()))
                .thenReturn(produtos.stream()
                        .filter(p -> p.getAtualizar().toLocalDate().equals(LocalDate.now()))
                        .collect(Collectors.toList())
                );

        List<ProdutoDTO> produtoDTOS = produtoService.findByDiaAtualizar(LocalDate.now());

        assertEquals(1,produtoDTOS.size());
        assertEquals(2,produtos.size());
    }

    @Test
    void testAtualizar(){
        Produto produto1 = new Produto();
        produto1.setNomeProduto("nome");

        Tipo tipo1 = new Tipo();
        produto1.setTipo(tipo1);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(new Produto()));

        assertDoesNotThrow(()-> produtoService.atualizar(1L, produto1));

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void testAtualizarException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> produtoService.atualizar(1L, produto));

        assertEquals("ID Inválido!", exception.getMessage());

        verify(produtoRepository, never()).save(any(Produto.class));
    }

    @Test
    void testDeletarIdInvalido(){
        when(produtoRepository.findById(2L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> produtoService.deletar(2L));

        assertEquals("ID Invalido", exception.getMessage());

        verify(produtoRepository, never()).save(any(Produto.class));
    }
}
