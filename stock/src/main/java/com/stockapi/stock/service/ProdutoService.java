package com.stockapi.stock.service;

import com.stockapi.stock.dto.ProdutoDTO;
import com.stockapi.stock.entity.Produto;
import com.stockapi.stock.entity.Tipo;
import com.stockapi.stock.repository.ProdutoRepository;
import com.stockapi.stock.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private TipoRepository tipoRepository;

    @Autowired
    public ProdutoService (ProdutoRepository produtoRepository, TipoRepository tipoRepository) {
        this.produtoRepository = produtoRepository;
        this.tipoRepository = tipoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<ProdutoDTO> findById(Long id){ return produtoRepository.findById(id).map(ProdutoDTO::new);}

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findByNome(String nome){
        Produto produto = produtoRepository.findByNomeProduto(nome);
        return new ProdutoDTO(produto);
    }

    @Transactional(readOnly = true)
    public  List<ProdutoDTO> findByAtivo(boolean ativo){
        List<Produto> produtos = produtoRepository.findByAtivo(ativo);
        return produtos.stream()
                .map(ProdutoDTO::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByDiaRegistro(LocalDate registro){
        List<Produto> produtos = produtoRepository.findByDiaRegistro(registro);

        return produtos.stream()
                .map(ProdutoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByDiaAtualizar(LocalDate atualizar){
        List<Produto> produtos = produtoRepository.findByDiaAtualizar(atualizar);

        return produtos.stream()
                .map(ProdutoDTO::new)
                .toList();
    }

    public void validarProduto(final Produto produto){
        String nome = produto.getNomeProduto();

        if (nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome De Produto Não Preenchido");
        }
        if (!nome.matches("[a-zA-Z0-9 ]+")){
            throw new IllegalArgumentException("Nome De Produto Invalido");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Produto produto){
        validarProduto(produto);
        Tipo tipo = tipoRepository.findById(produto.getTipo().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo não encontrado"));
        produto.setTipo(tipo);
        produtoRepository.save(produto);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Produto produto) {

        Optional<Produto> produtoExistenteOptional = produtoRepository.findById(id);

        if (produtoExistenteOptional.isPresent()) {
            Produto produtoExistente = produtoExistenteOptional.get();

            if (produto.getNomeProduto() != null) {
                validarProduto(produto);
                produtoExistente.setNomeProduto(produto.getNomeProduto());
            }

            if (produto.getTipo() != null) {
                produtoExistente.setTipo(produto.getTipo());
            }
            if (produto.getDescricao() != null) {
                produtoExistente.setDescricao(produto.getDescricao());
            }
            produtoRepository.save(produtoExistente);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deletar(Long id){
        Optional<Produto> produtoExistenteOptional = produtoRepository.findById(id);

        if (produtoExistenteOptional.isPresent()){
            Produto produtoExistente = produtoExistenteOptional.get();
            produtoExistente.setAtivo(false);
        } else {
            throw new IllegalArgumentException("ID Invalido");
        }
    }
}
