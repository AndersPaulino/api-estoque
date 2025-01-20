package com.stockapi.stock.service;

import com.stockapi.stock.dto.TipoDTO;
import com.stockapi.stock.entity.Tipo;
import com.stockapi.stock.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    private TipoRepository tipoRepository;

    @Autowired
    public TipoService(TipoRepository tipoRepository){
        this.tipoRepository = tipoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<TipoDTO> findById(Long id){
        return  tipoRepository.findById(id).map(TipoDTO::new);
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> findAll(){
        List<Tipo> tipos = tipoRepository.findAll();
        return tipos.stream().map(TipoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> findByAtivo(boolean ativo){
        List<Tipo> tipos = tipoRepository.findByAtivo(ativo);
        return tipos.stream().map(TipoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> findByNomeTipo(String nomeTipo){
        List<Tipo> tipos = tipoRepository.findByNomeTipo(nomeTipo);
        return tipos.stream().map(TipoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> findByDiaRegistro(LocalDate registro) {
        List<Tipo> tipos = tipoRepository.findByDiaRegistro(registro);

        return tipos.stream()
                .map(TipoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> findByDiaAtualizar(LocalDate atualizar) {
        List<Tipo> tipos = tipoRepository.findByDiaAtualizar(atualizar);

        return tipos.stream()
                .map(TipoDTO::new)
                .toList();
    }

    public void validarTipo(final Tipo tipo){
        String nameTipo = tipo.getNomeTipo();

        if (nameTipo == null || nameTipo.isEmpty()) {
            throw new IllegalArgumentException("Nome do Tipo não informado!");
        }
        if (!nameTipo.matches("[a-zA-Z0-9 ]+")){
            throw new IllegalArgumentException("Nome do Tipo inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Tipo tipo){
        validarTipo(tipo);
        tipoRepository.save(tipo);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Tipo tipo) {
        validarTipo(tipo);
        Optional<Tipo> tipoOptional = tipoRepository.findById(id);

        if (tipoOptional.isPresent()) {
            Tipo tipoExistente = tipoOptional.get();

            tipoExistente.setNomeTipo(tipo.getNomeTipo());

            tipoRepository.save(tipoExistente);
        } else {
            throw new IllegalArgumentException("ID de tipo inválido!");
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deletar(Long id){
        Optional<Tipo> tipoOptional = tipoRepository.findById(id);

        if (tipoOptional.isPresent()){
            Tipo tipo = tipoOptional.get();
            tipo.setAtivo(false);
        } else{
            throw new IllegalArgumentException("ID de tipo inválido!");
        }
    }

}
