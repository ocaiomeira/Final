package com.projetoEstoque.ControleEstoque.controller;

import com.projetoEstoque.ControleEstoque.domain.Categoria;
import com.projetoEstoque.ControleEstoque.requests.CategoriaPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.CategoriaPutRequestBody;
import com.projetoEstoque.ControleEstoque.service.CategoriaService;
import com.projetoEstoque.ControleEstoque.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("categoria")
@Log4j2
@RequiredArgsConstructor
public class CategoriaController{
    private final DateUtil dateUtil;
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(categoriaService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable long id) {
        return ResponseEntity.ok(categoriaService.findByIdOrThrowBadRequestException(id));
    }
    @GetMapping(path = "/find")
    public ResponseEntity<List<Categoria>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(categoriaService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody @Valid CategoriaPostRequestBody categoriaPostRequestBody) {
        return new ResponseEntity<>(categoriaService.save(categoriaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        categoriaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody CategoriaPutRequestBody categoriaPutRequestBody) {
        categoriaService.replace(categoriaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
