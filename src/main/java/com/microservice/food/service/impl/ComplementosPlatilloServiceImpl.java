package com.microservice.food.service.impl;

import com.microservice.food.service.ComplementosPlatilloService;
import com.microservice.food.domain.ComplementosPlatillo;
import com.microservice.food.repository.ComplementosPlatilloRepository;
import com.microservice.food.service.dto.ComplementosPlatilloDTO;
import com.microservice.food.service.mapper.ComplementosPlatilloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ComplementosPlatillo}.
 */
@Service
@Transactional
public class ComplementosPlatilloServiceImpl implements ComplementosPlatilloService {

    private final Logger log = LoggerFactory.getLogger(ComplementosPlatilloServiceImpl.class);

    private final ComplementosPlatilloRepository complementosPlatilloRepository;

    private final ComplementosPlatilloMapper complementosPlatilloMapper;

    public ComplementosPlatilloServiceImpl(ComplementosPlatilloRepository complementosPlatilloRepository, ComplementosPlatilloMapper complementosPlatilloMapper) {
        this.complementosPlatilloRepository = complementosPlatilloRepository;
        this.complementosPlatilloMapper = complementosPlatilloMapper;
    }

    @Override
    public ComplementosPlatilloDTO save(ComplementosPlatilloDTO complementosPlatilloDTO) {
        log.debug("Request to save ComplementosPlatillo : {}", complementosPlatilloDTO);
        ComplementosPlatillo complementosPlatillo = complementosPlatilloMapper.toEntity(complementosPlatilloDTO);
        complementosPlatillo = complementosPlatilloRepository.save(complementosPlatillo);
        return complementosPlatilloMapper.toDto(complementosPlatillo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplementosPlatilloDTO> findAll() {
        log.debug("Request to get all ComplementosPlatillos");
        return complementosPlatilloRepository.findAll().stream()
            .map(complementosPlatilloMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ComplementosPlatilloDTO> findOne(Long id) {
        log.debug("Request to get ComplementosPlatillo : {}", id);
        return complementosPlatilloRepository.findById(id)
            .map(complementosPlatilloMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComplementosPlatillo : {}", id);
        complementosPlatilloRepository.deleteById(id);
    }
}
