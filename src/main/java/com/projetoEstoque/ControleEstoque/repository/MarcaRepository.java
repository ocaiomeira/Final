package com.projetoEstoque.ControleEstoque.repository;

import com.projetoEstoque.ControleEstoque.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByName(String name);
}
