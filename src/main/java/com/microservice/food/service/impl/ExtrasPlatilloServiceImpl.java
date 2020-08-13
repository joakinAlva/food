package com.microservice.food.service.impl;

import com.microservice.food.service.ExtrasPlatilloService;
import com.microservice.food.domain.ExtrasPlatillo;
import com.microservice.food.repository.ExtrasPlatilloRepository;
import com.microservice.food.service.dto.ExtrasPlatilloDTO;
import com.microservice.food.service.mapper.ExtrasPlatilloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ExtrasPlatillo}.
 */
@Service
@Transactional
public class ExtrasPlatilloServiceImpl implements ExtrasPlatilloService {

    private final Logger log = LoggerFactory.getLogger(ExtrasPlatilloServiceImpl.class);

    private final ExtrasPlatilloRepository extrasPlatilloRepository;

    private final ExtrasPlatilloMapper extrasPlatilloMapper;

    public ExtrasPlatilloServiceImpl(ExtrasPlatilloRepository extrasPlatilloRepository, ExtrasPlatilloMapper extrasPlatilloMapper) {
        this.extrasPlatilloRepository = extrasPlatilloRepository;
        this.extrasPlatilloMapper = extrasPlatilloMapper;
    }

    @Override
    public ExtrasPlatilloDTO save(ExtrasPlatilloDTO extrasPlatilloDTO) {
        log.debug("Request to save ExtrasPlatillo : {}", extrasPlatilloDTO);
        ExtrasPlatillo extrasPlatillo = extrasPlatilloMapper.toEntity(extrasPlatilloDTO);
        extrasPlatillo = extrasPlatilloRepository.save(extrasPlatillo);
        return extrasPlatilloMapper.toDto(extrasPlatillo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExtrasPlatilloDTO> findAll() {
        log.debug("Request to get all ExtrasPlatillos");
        return extrasPlatilloRepository.findAll().stream()
            .map(extrasPlatilloMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ExtrasPlatilloDTO> findOne(Long id) {
        log.debug("Request to get ExtrasPlatillo : {}", id);
        return extrasPlatilloRepository.findById(id)
            .map(extrasPlatilloMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExtrasPlatillo : {}", id);
        extrasPlatilloRepository.deleteById(id);
    }
}
