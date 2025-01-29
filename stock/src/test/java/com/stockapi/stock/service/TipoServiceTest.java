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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

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
}
