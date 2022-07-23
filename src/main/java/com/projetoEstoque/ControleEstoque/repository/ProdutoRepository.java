package com.projetoEstoque.ControleEstoque.repository;

import com.projetoEstoque.ControleEstoque.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByName(String name);
}
