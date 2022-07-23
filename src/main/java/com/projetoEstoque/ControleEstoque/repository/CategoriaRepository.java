package com.projetoEstoque.ControleEstoque.repository;

import com.projetoEstoque.ControleEstoque.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByName(String name);
}
