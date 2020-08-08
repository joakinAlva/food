package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.MenuRestauranteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MenuRestaurante} and its DTO {@link MenuRestauranteDTO}.
 */
@Mapper(componentModel = "spring", uses = {RestauranteMapper.class})
public interface MenuRestauranteMapper extends EntityMapper<MenuRestauranteDTO, MenuRestaurante> {

    @Mapping(source = "restaurante.id", target = "restauranteId")
    MenuRestauranteDTO toDto(MenuRestaurante menuRestaurante);

    @Mapping(target = "menuRestaurantePlatillos", ignore = true)
    @Mapping(target = "removeMenuRestaurantePlatillo", ignore = true)
    @Mapping(source = "restauranteId", target = "restaurante")
    MenuRestaurante toEntity(MenuRestauranteDTO menuRestauranteDTO);

    default MenuRestaurante fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuRestaurante menuRestaurante = new MenuRestaurante();
        menuRestaurante.setId(id);
        return menuRestaurante;
    }
}
