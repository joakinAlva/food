package com.microservice.food.service.mapper;


import com.microservice.food.domain.*;
import com.microservice.food.service.dto.ExtraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Extra} and its DTO {@link ExtraDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExtraMapper extends EntityMapper<ExtraDTO, Extra> {


    @Mapping(target = "extrasPlatillos", ignore = true)
    @Mapping(target = "removeExtrasPlatillos", ignore = true)
    Extra toEntity(ExtraDTO extraDTO);

    default Extra fromId(Long id) {
        if (id == null) {
            return null;
        }
        Extra extra = new Extra();
        extra.setId(id);
        return extra;
    }
}
