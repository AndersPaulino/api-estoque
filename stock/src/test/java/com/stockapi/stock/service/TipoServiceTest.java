package com.stockapi.stock.service;

import com.stockapi.stock.dto.TipoDTO;
import com.stockapi.stock.entity.Tipo;
import com.stockapi.stock.repository.TipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TipoServiceTest {

    @Mock
    private TipoRepository tipoRepository;

    private TipoService tipoService;

    private Tipo tipo;

    private TipoDTO tipoDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        tipoService = new TipoService(tipoRepository);
        tipo = new Tipo();
        tipo.setNomeTipo("tipo");
        tipoDTO = new TipoDTO(tipo);
    }

    @Test
    void findByIdNotFound(){
        Long id = 1L;
        when(tipoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<TipoDTO> result = tipoService.findById(id);

        assertFalse(result.isPresent());
    }

    @Test
    void findByAtivoReturnTipoDTOs(){
        List<Tipo> tipoList = Stream.of(new Tipo()).collect(Collectors.toList());

        when(tipoRepository.findAll()).thenReturn(tipoList);

        List<TipoDTO> result = tipoService.findAll();

        assertEquals(tipoList.size(), result.size());
    }

    @Test
    void testValidarTipo(){
        tipo.setNomeTipo("Tipo1");

        assertDoesNotThrow(() -> tipoService.validarTipo(tipo));
    }

    @Test
    void testValidarTipoIsNull(){
        Tipo tipo1 = new Tipo();

        Tipo tipo2 = new Tipo();

        tipo.setNomeTipo(null);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tipoService.validarTipo(tipo));

        assertEquals("Nome do Tipo não informado!", e.getMessage());

        tipo1.setNomeTipo("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tipoService.validarTipo(tipo1);
        });

        assertEquals("Nome do Tipo não informado!", exception.getMessage());

        tipo2.setNomeTipo("Estoque@");

        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> {
                    tipoService.validarTipo(tipo2);
                });

        assertEquals("Nome do Tipo inválido!", exception1.getMessage());
    }

    @Test
    void testCadastrarTipo(){
        tipo.setNomeTipo("Tipo1");

        when(tipoRepository.save(tipo)).thenReturn(tipo);

        assertDoesNotThrow(() -> tipoService.cadastrar(tipo));

        verify(tipoRepository, times(1)).save(tipo);
    }

    @Test
    void testAtualizarIdAndTipoExists(){
        tipo.setNomeTipo("Tipo1");

        when(tipoRepository.findById(1L)).thenReturn(Optional.of(new Tipo()));

        assertDoesNotThrow(() -> tipoService.atualizar(1L, tipo));

        verify(tipoRepository, times(1)).save(any(Tipo.class));
    }

    @Test
    void testThrowExceptionInvalidTipo(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tipoService.atualizar(1L, tipo));

        assertEquals("ID de tipo inválido!", e.getMessage());

        verify(tipoRepository, never()).save(any(Tipo.class));
    }

    @Test
    void testThrowExceptionIdDoesNotExist(){
        tipo.setNomeTipo("NovoTipo");

        when(tipoRepository.findById(2L)).thenReturn(Optional.empty());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,  () -> tipoService.atualizar(2L,tipo));

        assertEquals("ID de tipo inválido!", e.getMessage());

        verify(tipoRepository, never()).save(any(Tipo.class));
    }

    @Test
    void tesstCadastrar(){
        tipo.setNomeTipo("Nome");

        tipoService.cadastrar(tipo);

        verify(tipoRepository, times(1)).save(tipo);
    }

    @Test
    void testDeletarIdExist(){
        when(tipoRepository.findById(1L)).thenReturn(Optional.of(tipo));

        assertDoesNotThrow(()-> tipoService.deletar(1L));

        assertFalse(tipo.isAtivo());
    }

    @Test
    void testDeletarThrowExceptionIdDoesNotExist(){
        when(tipoRepository.findById(2L)).thenReturn(Optional.empty());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()-> tipoService.deletar(2L));

        assertEquals("ID de tipo inválido!", e.getMessage());
    }

    @Test
    void testDeletarWhenTipoIsAlreadyInactive(){
        Tipo tipo1 = new Tipo();
        tipo1.setAtivo(false);

        when(tipoRepository.findById(1L)).thenReturn(Optional.of(tipo1));

        tipoService.deletar(1L);
        assertFalse(tipo1.isAtivo());
    }
}
