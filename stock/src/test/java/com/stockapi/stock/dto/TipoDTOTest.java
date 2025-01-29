package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoDTOTest {

    private Tipo tipo;
    private TipoDTO tipoDTO;

    @BeforeEach
    void setUp(){
        tipo = new Tipo();
        tipo.setNomeTipo("Camisa");
        tipoDTO = new TipoDTO(tipo);
    }

    @Test
    void testConstructorWithTipo(){
        assertEquals(tipo.getId(), tipoDTO.getId());
        assertEquals(tipo.getNomeTipo(), tipoDTO.getNomeTipo());
        assertEquals(tipo.getAtualizar(), tipoDTO.getAtualizar());
        assertEquals(tipo.getRegistro(), tipoDTO.getRegistro());
        assertEquals(tipo.isAtivo(), tipoDTO.isAtivo());
    }

    @Test
    void testConstructorWithIndividualParameters(){
        TipoDTO tipoDTO1 = new TipoDTO(1L, true, LocalDateTime.now(), LocalDateTime.now(), "Calca");

        assertEquals(1L, tipoDTO1.getId());
        assertEquals("Calca", tipoDTO1.getNomeTipo());
        assertEquals(true, tipoDTO1.isAtivo());
    }
}
