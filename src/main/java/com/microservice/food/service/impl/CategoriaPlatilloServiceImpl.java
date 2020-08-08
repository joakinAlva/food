package com.microservice.food.service.impl;

import com.microservice.food.service.CategoriaPlatilloService;
import com.microservice.food.domain.CategoriaPlatillo;
import com.microservice.food.repository.CategoriaPlatilloRepository;
import com.microservice.food.service.dto.CategoriaPlatilloDTO;
import com.microservice.food.service.mapper.CategoriaPlatilloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CategoriaPlatillo}.
 */
@Service
@Transactional
public class CategoriaPlatilloServiceImpl implements CategoriaPlatilloService {

    private final Logger log = LoggerFactory.getLogger(CategoriaPlatilloServiceImpl.class);

    private final CategoriaPlatilloRepository categoriaPlatilloRepository;

    private final CategoriaPlatilloMapper categoriaPlatilloMapper;

    public CategoriaPlatilloServiceImpl(CategoriaPlatilloRepository categoriaPlatilloRepository, CategoriaPlatilloMapper categoriaPlatilloMapper) {
        this.categoriaPlatilloRepository = categoriaPlatilloRepository;
        this.categoriaPlatilloMapper = categoriaPlatilloMapper;
    }

    @Override
    public CategoriaPlatilloDTO save(CategoriaPlatilloDTO categoriaPlatilloDTO) {
        log.debug("Request to save CategoriaPlatillo : {}", categoriaPlatilloDTO);
        CategoriaPlatillo categoriaPlatillo = categoriaPlatilloMapper.toEntity(categoriaPlatilloDTO);
        categoriaPlatillo = categoriaPlatilloRepository.save(categoriaPlatillo);
        return categoriaPlatilloMapper.toDto(categoriaPlatillo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaPlatilloDTO> findAll() {
        log.debug("Request to get all CategoriaPlatillos");
        return categoriaPlatilloRepository.findAll().stream()
            .map(categoriaPlatilloMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaPlatilloDTO> findOne(Long id) {
        log.debug("Request to get CategoriaPlatillo : {}", id);
        return categoriaPlatilloRepository.findById(id)
            .map(categoriaPlatilloMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoriaPlatillo : {}", id);
        categoriaPlatilloRepository.deleteById(id);
    }
}
