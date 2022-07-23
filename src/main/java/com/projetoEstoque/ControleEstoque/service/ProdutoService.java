package com.projetoEstoque.ControleEstoque.service;

import com.projetoEstoque.ControleEstoque.domain.Produto;
import com.projetoEstoque.ControleEstoque.exception.BadRequestException;
import com.projetoEstoque.ControleEstoque.mapper.ProdutoMapper;
import com.projetoEstoque.ControleEstoque.repository.ProdutoRepository;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.ProdutoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }
    public List<Produto> findByName(String name) {
        return produtoRepository.findByName(name);
    }
    public Produto findByIdOrThrowBadRequestException(long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Esse produto não esta cadastrado no sistema"));
    }
    @Transactional
    public Produto save(ProdutoPostRequestBody produtoPostRequestBody) {
        return produtoRepository.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequestBody));
    }

    public void delete(long id) {
        produtoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ProdutoPutRequestBody produtoPutRequestBody) {
        Produto savedProduto= findByIdOrThrowBadRequestException(produtoPutRequestBody.getId());
        Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequestBody);
        produto.setId(savedProduto.getId());
        produtoRepository.save(produto);
    }
}
