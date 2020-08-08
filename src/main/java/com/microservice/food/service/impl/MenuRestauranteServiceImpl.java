package com.microservice.food.service.impl;

import com.microservice.food.service.MenuRestauranteService;
import com.microservice.food.domain.MenuRestaurante;
import com.microservice.food.repository.MenuRestauranteRepository;
import com.microservice.food.service.dto.MenuRestauranteDTO;
import com.microservice.food.service.mapper.MenuRestauranteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MenuRestaurante}.
 */
@Service
@Transactional
public class MenuRestauranteServiceImpl implements MenuRestauranteService {

    private final Logger log = LoggerFactory.getLogger(MenuRestauranteServiceImpl.class);

    private final MenuRestauranteRepository menuRestauranteRepository;

    private final MenuRestauranteMapper menuRestauranteMapper;

    public MenuRestauranteServiceImpl(MenuRestauranteRepository menuRestauranteRepository, MenuRestauranteMapper menuRestauranteMapper) {
        this.menuRestauranteRepository = menuRestauranteRepository;
        this.menuRestauranteMapper = menuRestauranteMapper;
    }

    @Override
    public MenuRestauranteDTO save(MenuRestauranteDTO menuRestauranteDTO) {
        log.debug("Request to save MenuRestaurante : {}", menuRestauranteDTO);
        MenuRestaurante menuRestaurante = menuRestauranteMapper.toEntity(menuRestauranteDTO);
        menuRestaurante = menuRestauranteRepository.save(menuRestaurante);
        return menuRestauranteMapper.toDto(menuRestaurante);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuRestauranteDTO> findAll() {
        log.debug("Request to get all MenuRestaurantes");
        return menuRestauranteRepository.findAll().stream()
            .map(menuRestauranteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MenuRestauranteDTO> findOne(Long id) {
        log.debug("Request to get MenuRestaurante : {}", id);
        return menuRestauranteRepository.findById(id)
            .map(menuRestauranteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuRestaurante : {}", id);
        menuRestauranteRepository.deleteById(id);
    }
}
