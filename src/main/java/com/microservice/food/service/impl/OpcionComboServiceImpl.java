package com.microservice.food.service.impl;

import com.microservice.food.service.OpcionComboService;
import com.microservice.food.domain.OpcionCombo;
import com.microservice.food.repository.OpcionComboRepository;
import com.microservice.food.service.dto.OpcionComboDTO;
import com.microservice.food.service.mapper.OpcionComboMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link OpcionCombo}.
 */
@Service
@Transactional
public class OpcionComboServiceImpl implements OpcionComboService {

    private final Logger log = LoggerFactory.getLogger(OpcionComboServiceImpl.class);

    private final OpcionComboRepository opcionComboRepository;

    private final OpcionComboMapper opcionComboMapper;

    public OpcionComboServiceImpl(OpcionComboRepository opcionComboRepository, OpcionComboMapper opcionComboMapper) {
        this.opcionComboRepository = opcionComboRepository;
        this.opcionComboMapper = opcionComboMapper;
    }

    @Override
    public OpcionComboDTO save(OpcionComboDTO opcionComboDTO) {
        log.debug("Request to save OpcionCombo : {}", opcionComboDTO);
        OpcionCombo opcionCombo = opcionComboMapper.toEntity(opcionComboDTO);
        opcionCombo = opcionComboRepository.save(opcionCombo);
        return opcionComboMapper.toDto(opcionCombo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpcionComboDTO> findAll() {
        log.debug("Request to get all OpcionCombos");
        return opcionComboRepository.findAll().stream()
            .map(opcionComboMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OpcionComboDTO> findOne(Long id) {
        log.debug("Request to get OpcionCombo : {}", id);
        return opcionComboRepository.findById(id)
            .map(opcionComboMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OpcionCombo : {}", id);
        opcionComboRepository.deleteById(id);
    }
}
