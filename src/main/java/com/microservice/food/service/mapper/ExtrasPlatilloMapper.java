package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.ExtrasPlatilloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExtrasPlatillo} and its DTO {@link ExtrasPlatilloDTO}.
 */
@Mapper(componentModel = "spring", uses = {ExtraMapper.class, PlatilloMapper.class})
public interface ExtrasPlatilloMapper extends EntityMapper<ExtrasPlatilloDTO, ExtrasPlatillo> {

    @Mapping(source = "extra.id", target = "extraId")
    @Mapping(source = "platillo.id", target = "platilloId")
    ExtrasPlatilloDTO toDto(ExtrasPlatillo extrasPlatillo);

    @Mapping(source = "extraId", target = "extra")
    @Mapping(source = "platilloId", target = "platillo")
    ExtrasPlatillo toEntity(ExtrasPlatilloDTO extrasPlatilloDTO);

    default ExtrasPlatillo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExtrasPlatillo extrasPlatillo = new ExtrasPlatillo();
        extrasPlatillo.setId(id);
        return extrasPlatillo;
    }
}
