package com.stockapi.stock.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "cl_ativo",nullable = false)
    private boolean ativo;

    @Column(name = "cl_registro", nullable = false)
    private LocalDateTime registro;

    @Column(name = "cl_atualizar")
    private LocalDateTime atualizar;
    @PrePersist
    void prePersist(){
        this.registro = LocalDateTime.now();
        this.ativo = true;
    }
    @PreUpdate
    void preUpdate(){
        this.atualizar = LocalDateTime.now();
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

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setRegistro(LocalDateTime registro) {
        this.registro = registro;
    }

    public void setAtualizar(LocalDateTime atualizar) {
        this.atualizar = atualizar;
    }
}
