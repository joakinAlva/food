package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.PlatilloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Platillo} and its DTO {@link PlatilloDTO}.
 */
@Mapper(componentModel = "spring", uses = {RestauranteMapper.class, CategoriaPlatilloMapper.class, TipoPlatilloMapper.class})
public interface PlatilloMapper extends EntityMapper<PlatilloDTO, Platillo> {

    @Mapping(source = "restaurante.id", target = "restauranteId")
    @Mapping(source = "categoriaPlatillo.id", target = "categoriaPlatilloId")
    @Mapping(source = "tipoPlatillo.id", target = "tipoPlatilloId")
    PlatilloDTO toDto(Platillo platillo);

    @Mapping(target = "platillosCombos", ignore = true)
    @Mapping(target = "removePlatillosCombo", ignore = true)
    @Mapping(target = "extrasPlatillos", ignore = true)
    @Mapping(target = "removeExtrasPlatillos", ignore = true)
    @Mapping(source = "restauranteId", target = "restaurante")
    @Mapping(source = "categoriaPlatilloId", target = "categoriaPlatillo")
    @Mapping(source = "tipoPlatilloId", target = "tipoPlatillo")
    @Mapping(target = "menuRestaurantePlatillos", ignore = true)
    @Mapping(target = "removeMenuRestaurantePlatillo", ignore = true)
    Platillo toEntity(PlatilloDTO platilloDTO);

    default Platillo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Platillo platillo = new Platillo();
        platillo.setId(id);
        return platillo;
    }
}
