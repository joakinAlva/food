package com.microservice.food.service.impl;

import com.microservice.food.service.PlatillosComboService;
import com.microservice.food.domain.PlatillosCombo;
import com.microservice.food.repository.PlatillosComboRepository;
import com.microservice.food.service.dto.PlatillosComboDTO;
import com.microservice.food.service.mapper.PlatillosComboMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PlatillosCombo}.
 */
@Service
@Transactional
public class PlatillosComboServiceImpl implements PlatillosComboService {

    private final Logger log = LoggerFactory.getLogger(PlatillosComboServiceImpl.class);

    private final PlatillosComboRepository platillosComboRepository;

    private final PlatillosComboMapper platillosComboMapper;

    public PlatillosComboServiceImpl(PlatillosComboRepository platillosComboRepository, PlatillosComboMapper platillosComboMapper) {
        this.platillosComboRepository = platillosComboRepository;
        this.platillosComboMapper = platillosComboMapper;
    }

    @Override
    public PlatillosComboDTO save(PlatillosComboDTO platillosComboDTO) {
        log.debug("Request to save PlatillosCombo : {}", platillosComboDTO);
        PlatillosCombo platillosCombo = platillosComboMapper.toEntity(platillosComboDTO);
        platillosCombo = platillosComboRepository.save(platillosCombo);
        return platillosComboMapper.toDto(platillosCombo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlatillosComboDTO> findAll() {
        log.debug("Request to get all PlatillosCombos");
        return platillosComboRepository.findAll().stream()
            .map(platillosComboMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PlatillosComboDTO> findOne(Long id) {
        log.debug("Request to get PlatillosCombo : {}", id);
        return platillosComboRepository.findById(id)
            .map(platillosComboMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlatillosCombo : {}", id);
        platillosComboRepository.deleteById(id);
    }
}
