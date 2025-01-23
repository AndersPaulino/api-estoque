package com.stockapi.stock.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class TipoTest {

    private Tipo tipo;

    @BeforeEach
    void setUp() {
        tipo = new Tipo();
    }

    @Test
    void testPrePersist() {
       tipo.prePersist();
        assertNotNull(tipo.getRegistro(), "Registro não deve ser nulo após prePersist");
        assertTrue(tipo.isAtivo(), "Ativo deve ser true após prePersist");
    }

    @Test
    void testPreUpdate() {
        LocalDateTime previousUpdate = tipo.getAtualizar();
        tipo.preUpdate();
        assertNotNull(tipo.getAtualizar(), "Atualizar não deve ser nulo após preUpdate");
        assertNotEquals(previousUpdate, tipo.getAtualizar(), "O valor de 'atualizar' deve ser diferente após preUpdate");
    }

    @Test
    void testSettersAndGetters() {
        tipo.setAtivo(false);
        tipo.setRegistro(LocalDateTime.now().minusDays(1));
        tipo.setAtualizar(LocalDateTime.now());
        tipo.setNomeTipo("Tipo Teste");

        assertFalse(tipo.isAtivo(), "Ativo deve ser false após setter");
        assertEquals(tipo.getRegistro().toLocalDate(), LocalDateTime.now().minusDays(1).toLocalDate(), "A data de registro não corresponde");
        assertNotNull(tipo.getAtualizar(), "O campo 'atualizar' não deve ser nulo");
        assertEquals("Tipo Teste", tipo.getNomeTipo(), "O nome do tipo não foi definido corretamente");
    }
}
