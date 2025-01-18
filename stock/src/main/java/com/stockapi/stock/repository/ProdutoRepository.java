package com.stockapi.stock.repository;

import com.stockapi.stock.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@RequestMapping
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT e FROM Produto e WHERE e.nomeProduto = :nomeProduto")
    public Produto findByNomeProduto(@Param("nomeProduto") String nomeProduto);

    @Query("SELECT e FROM Produto e WHERE e.ativo = :ativo")
    public List<Produto> findByAtivo(@Param("ativo")boolean ativo);

    @Query("SELECT e FROM Produto e WHERE DATE(e.registro) = :registro")
    List<Produto> findByDiaRegistro(@Param("registro") LocalDate registro);

    @Query("SELECT e FROM Produto e WHERE DATE(e.atualizar) = :atualizar")
    List<Produto> findByDiaAtualizar(@Param("atualizar") LocalDate atualizar);
}
