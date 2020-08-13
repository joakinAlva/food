package com.microservice.food.service.impl;

import com.microservice.food.service.OpcionPlatilloService;
import com.microservice.food.domain.OpcionPlatillo;
import com.microservice.food.repository.OpcionPlatilloRepository;
import com.microservice.food.service.dto.OpcionPlatilloDTO;
import com.microservice.food.service.mapper.OpcionPlatilloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link OpcionPlatillo}.
 */
@Service
@Transactional
public class OpcionPlatilloServiceImpl implements OpcionPlatilloService {

    private final Logger log = LoggerFactory.getLogger(OpcionPlatilloServiceImpl.class);

    private final OpcionPlatilloRepository opcionPlatilloRepository;

    private final OpcionPlatilloMapper opcionPlatilloMapper;

    public OpcionPlatilloServiceImpl(OpcionPlatilloRepository opcionPlatilloRepository, OpcionPlatilloMapper opcionPlatilloMapper) {
        this.opcionPlatilloRepository = opcionPlatilloRepository;
        this.opcionPlatilloMapper = opcionPlatilloMapper;
    }

    @Override
    public OpcionPlatilloDTO save(OpcionPlatilloDTO opcionPlatilloDTO) {
        log.debug("Request to save OpcionPlatillo : {}", opcionPlatilloDTO);
        OpcionPlatillo opcionPlatillo = opcionPlatilloMapper.toEntity(opcionPlatilloDTO);
        opcionPlatillo = opcionPlatilloRepository.save(opcionPlatillo);
        return opcionPlatilloMapper.toDto(opcionPlatillo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpcionPlatilloDTO> findAll() {
        log.debug("Request to get all OpcionPlatillos");
        return opcionPlatilloRepository.findAll().stream()
            .map(opcionPlatilloMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OpcionPlatilloDTO> findOne(Long id) {
        log.debug("Request to get OpcionPlatillo : {}", id);
        return opcionPlatilloRepository.findById(id)
            .map(opcionPlatilloMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OpcionPlatillo : {}", id);
        opcionPlatilloRepository.deleteById(id);
    }
}
