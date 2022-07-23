package com.projetoEstoque.ControleEstoque.controller;

import com.projetoEstoque.ControleEstoque.domain.Marca;
import com.projetoEstoque.ControleEstoque.requests.MarcaPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.MarcaPutRequestBody;
import com.projetoEstoque.ControleEstoque.service.MarcaService;
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
@RequestMapping("marcas")
@Log4j2
@RequiredArgsConstructor
public class MarcaController{
    private final DateUtil dateUtil;
    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(marcaService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Marca> findById(@PathVariable long id) {
        return ResponseEntity.ok(marcaService.findByIdOrThrowBadRequestException(id));
    }
    @GetMapping(path = "/find")
    public ResponseEntity<List<Marca>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(marcaService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Marca> save(@RequestBody @Valid MarcaPostRequestBody marcaPostRequestBody) {
        return new ResponseEntity<>(marcaService.save(marcaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        marcaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MarcaPutRequestBody marcaPutRequestBody) {
        marcaService.replace(marcaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
