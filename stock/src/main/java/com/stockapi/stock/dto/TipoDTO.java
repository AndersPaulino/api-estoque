package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Tipo;

import java.time.LocalDateTime;

public class TipoDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private LocalDateTime atualizar;
    private String nomeTipo;

    public TipoDTO(Tipo tipo){
        id = tipo.getId();
        ativo = tipo.isAtivo();
        registro = tipo.getRegistro();
        atualizar = tipo.getAtualizar();
        nomeTipo = tipo.getNomeTipo();
    }

    public TipoDTO(Long id, boolean ativo, LocalDateTime registro, LocalDateTime atualizar, String nomeTipo){
        this.id = id;
        this.ativo = ativo;
        this.registro = registro;
        this.atualizar = atualizar;
        this.nomeTipo = nomeTipo;
    }

    public Long getId() {
        return id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getRegistro() {
        return registro;
    }

    public LocalDateTime getAtualizar() {
        return atualizar;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

}
