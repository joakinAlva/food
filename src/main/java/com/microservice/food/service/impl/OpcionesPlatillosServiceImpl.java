package com.microservice.food.service.impl;

import com.microservice.food.service.OpcionesPlatillosService;
import com.microservice.food.domain.OpcionesPlatillos;
import com.microservice.food.repository.OpcionesPlatillosRepository;
import com.microservice.food.service.dto.OpcionesPlatillosDTO;
import com.microservice.food.service.mapper.OpcionesPlatillosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link OpcionesPlatillos}.
 */
@Service
@Transactional
public class OpcionesPlatillosServiceImpl implements OpcionesPlatillosService {

    private final Logger log = LoggerFactory.getLogger(OpcionesPlatillosServiceImpl.class);

    private final OpcionesPlatillosRepository opcionesPlatillosRepository;

    private final OpcionesPlatillosMapper opcionesPlatillosMapper;

    public OpcionesPlatillosServiceImpl(OpcionesPlatillosRepository opcionesPlatillosRepository, OpcionesPlatillosMapper opcionesPlatillosMapper) {
        this.opcionesPlatillosRepository = opcionesPlatillosRepository;
        this.opcionesPlatillosMapper = opcionesPlatillosMapper;
    }

    @Override
    public OpcionesPlatillosDTO save(OpcionesPlatillosDTO opcionesPlatillosDTO) {
        log.debug("Request to save OpcionesPlatillos : {}", opcionesPlatillosDTO);
        OpcionesPlatillos opcionesPlatillos = opcionesPlatillosMapper.toEntity(opcionesPlatillosDTO);
        opcionesPlatillos = opcionesPlatillosRepository.save(opcionesPlatillos);
        return opcionesPlatillosMapper.toDto(opcionesPlatillos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpcionesPlatillosDTO> findAll() {
        log.debug("Request to get all OpcionesPlatillos");
        return opcionesPlatillosRepository.findAll().stream()
            .map(opcionesPlatillosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OpcionesPlatillosDTO> findOne(Long id) {
        log.debug("Request to get OpcionesPlatillos : {}", id);
        return opcionesPlatillosRepository.findById(id)
            .map(opcionesPlatillosMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OpcionesPlatillos : {}", id);
        opcionesPlatillosRepository.deleteById(id);
    }
}
