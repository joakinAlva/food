package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.CategoriaPlatilloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoriaPlatillo} and its DTO {@link CategoriaPlatilloDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoriaPlatilloMapper extends EntityMapper<CategoriaPlatilloDTO, CategoriaPlatillo> {


    @Mapping(target = "platillos", ignore = true)
    @Mapping(target = "removePlatillos", ignore = true)
    CategoriaPlatillo toEntity(CategoriaPlatilloDTO categoriaPlatilloDTO);

    default CategoriaPlatillo fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoriaPlatillo categoriaPlatillo = new CategoriaPlatillo();
        categoriaPlatillo.setId(id);
        return categoriaPlatillo;
    }
}
