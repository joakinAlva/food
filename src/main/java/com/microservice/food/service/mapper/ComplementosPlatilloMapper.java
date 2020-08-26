package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.ComplementosPlatilloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ComplementosPlatillo} and its DTO {@link ComplementosPlatilloDTO}.
 */
@Mapper(componentModel = "spring", uses = {ComplementoMapper.class, PlatilloMapper.class})
public interface ComplementosPlatilloMapper extends EntityMapper<ComplementosPlatilloDTO, ComplementosPlatillo> {

    @Mapping(source = "complemento.id", target = "complementoId")
    @Mapping(source = "platillo.id", target = "platilloId")
    ComplementosPlatilloDTO toDto(ComplementosPlatillo complementosPlatillo);

    @Mapping(source = "complementoId", target = "complemento")
    @Mapping(source = "platilloId", target = "platillo")
    ComplementosPlatillo toEntity(ComplementosPlatilloDTO complementosPlatilloDTO);

    default ComplementosPlatillo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComplementosPlatillo complementosPlatillo = new ComplementosPlatillo();
        complementosPlatillo.setId(id);
        return complementosPlatillo;
    }
}
