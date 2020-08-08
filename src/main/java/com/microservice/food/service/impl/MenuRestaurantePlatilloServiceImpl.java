package com.microservice.food.service.impl;

import com.microservice.food.service.MenuRestaurantePlatilloService;
import com.microservice.food.domain.MenuRestaurantePlatillo;
import com.microservice.food.repository.MenuRestaurantePlatilloRepository;
import com.microservice.food.service.dto.MenuRestaurantePlatilloDTO;
import com.microservice.food.service.mapper.MenuRestaurantePlatilloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MenuRestaurantePlatillo}.
 */
@Service
@Transactional
public class MenuRestaurantePlatilloServiceImpl implements MenuRestaurantePlatilloService {

    private final Logger log = LoggerFactory.getLogger(MenuRestaurantePlatilloServiceImpl.class);

    private final MenuRestaurantePlatilloRepository menuRestaurantePlatilloRepository;

    private final MenuRestaurantePlatilloMapper menuRestaurantePlatilloMapper;

    public MenuRestaurantePlatilloServiceImpl(MenuRestaurantePlatilloRepository menuRestaurantePlatilloRepository, MenuRestaurantePlatilloMapper menuRestaurantePlatilloMapper) {
        this.menuRestaurantePlatilloRepository = menuRestaurantePlatilloRepository;
        this.menuRestaurantePlatilloMapper = menuRestaurantePlatilloMapper;
    }

    @Override
    public MenuRestaurantePlatilloDTO save(MenuRestaurantePlatilloDTO menuRestaurantePlatilloDTO) {
        log.debug("Request to save MenuRestaurantePlatillo : {}", menuRestaurantePlatilloDTO);
        MenuRestaurantePlatillo menuRestaurantePlatillo = menuRestaurantePlatilloMapper.toEntity(menuRestaurantePlatilloDTO);
        menuRestaurantePlatillo = menuRestaurantePlatilloRepository.save(menuRestaurantePlatillo);
        return menuRestaurantePlatilloMapper.toDto(menuRestaurantePlatillo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuRestaurantePlatilloDTO> findAll() {
        log.debug("Request to get all MenuRestaurantePlatillos");
        return menuRestaurantePlatilloRepository.findAllWithEagerRelationships().stream()
            .map(menuRestaurantePlatilloMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    public Page<MenuRestaurantePlatilloDTO> findAllWithEagerRelationships(Pageable pageable) {
        return menuRestaurantePlatilloRepository.findAllWithEagerRelationships(pageable).map(menuRestaurantePlatilloMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MenuRestaurantePlatilloDTO> findOne(Long id) {
        log.debug("Request to get MenuRestaurantePlatillo : {}", id);
        return menuRestaurantePlatilloRepository.findOneWithEagerRelationships(id)
            .map(menuRestaurantePlatilloMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuRestaurantePlatillo : {}", id);
        menuRestaurantePlatilloRepository.deleteById(id);
    }
}
