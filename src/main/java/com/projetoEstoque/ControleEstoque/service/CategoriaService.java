package com.projetoEstoque.ControleEstoque.service;

import com.projetoEstoque.ControleEstoque.domain.Categoria;
import com.projetoEstoque.ControleEstoque.exception.BadRequestException;
import com.projetoEstoque.ControleEstoque.mapper.CategoriaMapper;
import com.projetoEstoque.ControleEstoque.repository.CategoriaRepository;
import com.projetoEstoque.ControleEstoque.requests.CategoriaPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.CategoriaPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    public List<Categoria> listAll() {
        return categoriaRepository.findAll();
    }
    public List<Categoria> findByName(String name) {
        return categoriaRepository.findByName(name);
    }
    public Categoria findByIdOrThrowBadRequestException(long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Essa categoria não esta cadastrada no sistema"));
    }
    @Transactional
    public Categoria save(CategoriaPostRequestBody categoriaPostRequestBody) {
        return categoriaRepository.save(CategoriaMapper.INSTANCE.toCategoria(categoriaPostRequestBody));
    }

    public void delete(long id) {
        categoriaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(CategoriaPutRequestBody categoriaPutRequestBody) {
        Categoria savedCategoria= findByIdOrThrowBadRequestException(categoriaPutRequestBody.getId());
        Categoria categoria = CategoriaMapper.INSTANCE.toCategoria(categoriaPutRequestBody);
        categoria.setId(savedCategoria.getId());
        categoriaRepository.save(categoria);
    }
}
