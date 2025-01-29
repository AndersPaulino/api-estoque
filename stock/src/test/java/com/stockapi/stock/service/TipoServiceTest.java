package com.stockapi.stock.service;

import com.stockapi.stock.dto.TipoDTO;
import com.stockapi.stock.entity.Tipo;
import com.stockapi.stock.repository.TipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
