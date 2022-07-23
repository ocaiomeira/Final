package com.projetoEstoque.ControleEstoque.mapper;
import com.projetoEstoque.ControleEstoque.domain.Marca;
import com.projetoEstoque.ControleEstoque.requests.MarcaPostRequestBody;
import com.projetoEstoque.ControleEstoque.requests.MarcaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class MarcaMapper {
    public static final MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);
    public abstract Marca toMarca(MarcaPostRequestBody marcaPostRequestBody);
    public abstract Marca toMarca(MarcaPutRequestBody marcaPostRequestBody);


}
