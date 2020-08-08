package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.RestauranteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restaurante} and its DTO {@link RestauranteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RestauranteMapper extends EntityMapper<RestauranteDTO, Restaurante> {


    @Mapping(target = "platillos", ignore = true)
    @Mapping(target = "removePlatillos", ignore = true)
    @Mapping(target = "menuRestaurantes", ignore = true)
    @Mapping(target = "removeMenuRestaurantes", ignore = true)
    Restaurante toEntity(RestauranteDTO restauranteDTO);

    default Restaurante fromId(Long id) {
        if (id == null) {
            return null;
        }
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);
        return restaurante;
    }
}
