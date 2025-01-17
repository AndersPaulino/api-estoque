package com.stockapi.stock.dto;

import com.stockapi.stock.entity.Tipo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
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
}
