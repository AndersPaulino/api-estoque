package com.stockapi.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "cl_ativo",nullable = false)
    private boolean ativo;
    @Getter @Setter
    @Column(name = "cl_registro", nullable = false)
    private LocalDateTime registro;
    @Getter @Setter
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
}
