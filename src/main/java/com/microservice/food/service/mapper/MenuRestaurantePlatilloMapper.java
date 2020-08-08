package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.MenuRestaurantePlatilloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MenuRestaurantePlatillo} and its DTO {@link MenuRestaurantePlatilloDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlatilloMapper.class, MenuRestauranteMapper.class})
public interface MenuRestaurantePlatilloMapper extends EntityMapper<MenuRestaurantePlatilloDTO, MenuRestaurantePlatillo> {

    @Mapping(source = "menuRestaurante.id", target = "menuRestauranteId")
    MenuRestaurantePlatilloDTO toDto(MenuRestaurantePlatillo menuRestaurantePlatillo);

    @Mapping(target = "removePlatillos", ignore = true)
    @Mapping(source = "menuRestauranteId", target = "menuRestaurante")
    MenuRestaurantePlatillo toEntity(MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO);

    default MenuRestaurantePlatillo fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuRestaurantePlatillo menuRestaurantePlatillo = new MenuRestaurantePlatillo();
        menuRestaurantePlatillo.setId(id);
        return menuRestaurantePlatillo;
    }
}
