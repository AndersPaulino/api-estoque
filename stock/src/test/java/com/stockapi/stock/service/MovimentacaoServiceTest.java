package com.stockapi.stock.service;

import com.stockapi.stock.dto.MovimentacaoDTO;
import com.stockapi.stock.entity.Movimentacao;
import com.stockapi.stock.entity.Produto;
import com.stockapi.stock.repository.MovimentacaoRepository;
import com.stockapi.stock.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    private Produto produto;

    private MovimentacaoService movimentacaoService;

    private Movimentacao movimentacao;

    private MovimentacaoDTO movimentacaoDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        movimentacaoService = new MovimentacaoService(movimentacaoRepository);
        movimentacao = new Movimentacao();
        produto = new Produto();
        movimentacao.setProduto(produto);
    }

    @Test
    void testFindByIdNotFound() {
        Long id = 1L;
        when(movimentacaoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<MovimentacaoDTO> result = movimentacaoService.findById(id);

        assertFalse(result.isPresent());
    }



}
