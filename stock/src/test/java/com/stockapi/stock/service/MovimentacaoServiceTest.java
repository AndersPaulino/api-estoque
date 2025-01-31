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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testFindAll(){
        List<Movimentacao> movimentacaoList = Stream.of(new Movimentacao(), new Movimentacao())
                .collect(Collectors.toList());

        when(movimentacaoRepository.findAll()).thenReturn(movimentacaoList);

        List<MovimentacaoDTO> result = movimentacaoService.findAll();

        assertEquals(movimentacaoList.size(), result.size());
    }

    @Test
    void testFindByAtivo(){
        boolean ativo = true;
        List<Movimentacao> movimentacaoList = Stream.of(new Movimentacao(), new Movimentacao())
                .collect(Collectors.toList());

        when(movimentacaoRepository.findByAtivo(ativo)).thenReturn(movimentacaoList);

        List<MovimentacaoDTO> result = movimentacaoService.findByAtivo(ativo);

        assertEquals(movimentacaoList.size(), result.size());
    }

    @Test
    void testFindByDiaRegistro(){
        LocalDate registro = LocalDate.now();
        List<Movimentacao> movimentacaoList = Stream.of(new Movimentacao(), new Movimentacao())
                .collect(Collectors.toList());

        when(movimentacaoRepository.findByDiaRegistro(registro)).thenReturn(movimentacaoList);

        List<MovimentacaoDTO> result = movimentacaoService.findByDiaRegistro(registro);

        assertEquals(movimentacaoList.size(), result.size());
    }
}
