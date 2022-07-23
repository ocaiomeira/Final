package com.projetoEstoque.ControleEstoque.service;

import com.projetoEstoque.ControleEstoque.domain.Marca;
import com.projetoEstoque.ControleEstoque.exception.BadRequestException;
import com.projetoEstoque.ControleEstoque.mapper.MarcaMapper;
import com.projetoEstoque.ControleEstoque.repository.MarcaRepository;
import com.projetoEstoque.ControleEstoque.requests.MarcaPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.MarcaPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    public List<Marca> listAll() {
        return marcaRepository.findAll();
    }
    public List<Marca> findByName(String name) {
        return marcaRepository.findByName(name);
    }
    public Marca findByIdOrThrowBadRequestException(long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Essa Marca não esta cadastrada no sistema"));
    }
    @Transactional
    public Marca save(MarcaPostRequestBody marcaPostRequestBody) {
        return marcaRepository.save(MarcaMapper.INSTANCE.toMarca(marcaPostRequestBody));
    }

    public void delete(long id) {
        marcaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(MarcaPutRequestBody marcaPutRequestBody) {
        Marca savedMarca= findByIdOrThrowBadRequestException(marcaPutRequestBody.getId());
        Marca marca = MarcaMapper.INSTANCE.toMarca(marcaPutRequestBody);
        marca.setId(savedMarca.getId());
        marcaRepository.save(marca);
    }
}
