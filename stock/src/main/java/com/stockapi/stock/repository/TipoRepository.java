package com.stockapi.stock.repository;

import com.stockapi.stock.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
    @Query("SELECT e FROM Tipo e WHERE e.nomeTipo = :nomeTipo")
    public List<Tipo> findByNomeTipo(@Param("nome") String nomeTipo);
    @Query("SELECT e FROM Tipo e WHERE e.ativo = :ativo")
    public List<Tipo> findByAtivo(@Param("ativo")boolean ativo);
    @Query("SELECT e FROM Tipo e WHERE DATE(e.registro) = :registro")
    List<Tipo> findByDiaRegistro(@Param("registro") LocalDate registro);
    @Query("SELECT e FROM Tipo e WHERE DATE(e.atualizar) = :atualizar")
    List<Tipo> findByDiaAtualizar(@Param("atualizar") LocalDate atualizar);

}
