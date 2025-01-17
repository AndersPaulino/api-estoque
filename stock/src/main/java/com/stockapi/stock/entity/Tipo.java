package com.stockapi.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tipo", schema = "public")
public class Tipo extends AbstractEntity{

    @Column(name = "cl_nome_tipo")
    private String nomeTipo;

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
