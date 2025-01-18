package com.stockapi.stock.service;

import com.stockapi.stock.dto.MovimentacaoDTO;
import com.stockapi.stock.entity.Movimentacao;
import com.stockapi.stock.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository){
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoDTO> findByAtivo(boolean ativo){
        List<Movimentacao> movimentacaos = movimentacaoRepository.findByAtivo(ativo);
        return movimentacaos.stream()
                .map(MovimentacaoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoDTO> findByDiaRegistro(LocalDate registro){
        List<Movimentacao> movimentacaos = movimentacaoRepository.findByDiaRegistro(registro);

        return movimentacaos.stream()
                .map(MovimentacaoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoDTO> findByDiaAtualizar(LocalDate atualizar){
        List<Movimentacao> movimentacaos = movimentacaoRepository.findByDiaAtualizar(atualizar);

        return movimentacaos.stream()
                .map(MovimentacaoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<MovimentacaoDTO> findById(Long id) {
        return movimentacaoRepository.findById(id).map(movimentacao -> {
            MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO(movimentacao);
            return movimentacaoDTO;
        });
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoDTO> findAll() {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAll();
        return movimentacoes.stream().map(MovimentacaoDTO::new).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Movimentacao movimentacao) {
        if (movimentacao.getSaida() == null){
            movimentacao.setSaida(0);
        }
        if (movimentacao.getEntrada() < movimentacao.getSaida()){
            throw new IllegalArgumentException("Valor de Saida não pode ser maior que o valor de Entrada!");
        }
        movimentacao.setTotalProduto(movimentacao.getEntrada() - movimentacao.getSaida());
        movimentacaoRepository.save(movimentacao);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Movimentacao movimentacao) {
        Optional<Movimentacao> movimentacaoExistenteOptional = movimentacaoRepository.findById(id);

        if (movimentacaoExistenteOptional.isPresent()) {
            Movimentacao movimentacaoExistente = movimentacaoExistenteOptional.get();
            if (movimentacao.getEntrada() != null){
                movimentacaoExistente.setTotalProduto(movimentacaoExistente.getTotalProduto() + movimentacao.getEntrada());
            }
            if(movimentacao.getSaida() != null){
                movimentacaoExistente.setSaida(movimentacao.getSaida());
            }
            if (movimentacao.getSaida() == null){
                movimentacaoExistente.setSaida(0);
            }
            if (movimentacao.getProduto() != movimentacaoExistente.getProduto()){
                throw new IllegalArgumentException("O Produto não pode ser Alterado!");
            }
            if (movimentacao.getProduto() == movimentacaoExistente.getProduto()){
                movimentacaoExistente.setProduto(movimentacao.getProduto());
            }
            if (movimentacao.getProduto() == null){
                movimentacaoExistente.setProduto(movimentacaoExistente.getProduto());
            }
            movimentacaoExistente.setTotalProduto(movimentacaoExistente.getTotalProduto() - movimentacao.getSaida());

            movimentacaoRepository.save(movimentacaoExistente);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deletar(Long id){
        Optional<Movimentacao> movimentacaoExistenteOptional = movimentacaoRepository.findById(id);

        if (movimentacaoExistenteOptional.isPresent()){
            Movimentacao movimentacaoExistente = movimentacaoExistenteOptional.get();
            movimentacaoExistente.setAtivo(false);
        } else {
            throw new IllegalArgumentException("ID Invalido");
        }
    }
}
