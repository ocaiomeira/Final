package com.projetoEstoque.ControleEstoque.mapper;
import com.projetoEstoque.ControleEstoque.domain.Categoria;
import com.projetoEstoque.ControleEstoque.requests.CategoriaPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.CategoriaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CategoriaMapper {
    public static final CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);
    public abstract Categoria toCategoria(CategoriaPostRequestBody categoriaPostRequestBody);
    public abstract Categoria toCategoria(CategoriaPutRequestBody categoriaPostRequestBody);


}
