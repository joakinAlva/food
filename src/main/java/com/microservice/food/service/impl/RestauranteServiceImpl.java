package com.microservice.food.service.impl;

import com.microservice.food.service.RestauranteService;
import com.microservice.food.domain.Restaurante;
import com.microservice.food.repository.RestauranteRepository;
import com.microservice.food.service.dto.RestauranteDTO;
import com.microservice.food.service.mapper.RestauranteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Restaurante}.
 */
@Service
@Transactional
public class RestauranteServiceImpl implements RestauranteService {

    private final Logger log = LoggerFactory.getLogger(RestauranteServiceImpl.class);

    private final RestauranteRepository restauranteRepository;

    private final RestauranteMapper restauranteMapper;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository, RestauranteMapper restauranteMapper) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteMapper = restauranteMapper;
    }

    @Override
    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
        log.debug("Request to save Restaurante : {}", restauranteDTO);
        Restaurante restaurante = restauranteMapper.toEntity(restauranteDTO);
        restaurante = restauranteRepository.save(restaurante);
        return restauranteMapper.toDto(restaurante);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RestauranteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Restaurantes");
        return restauranteRepository.findAll(pageable)
            .map(restauranteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RestauranteDTO> findOne(Long id) {
        log.debug("Request to get Restaurante : {}", id);
        return restauranteRepository.findById(id)
            .map(restauranteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Restaurante : {}", id);
        restauranteRepository.deleteById(id);
    }
}
