package com.microservice.food.service.impl;

import com.microservice.food.service.TipoPlatilloService;
import com.microservice.food.domain.TipoPlatillo;
import com.microservice.food.repository.TipoPlatilloRepository;
import com.microservice.food.service.dto.TipoPlatilloDTO;
import com.microservice.food.service.mapper.TipoPlatilloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TipoPlatillo}.
 */
@Service
@Transactional
public class TipoPlatilloServiceImpl implements TipoPlatilloService {

    private final Logger log = LoggerFactory.getLogger(TipoPlatilloServiceImpl.class);

    private final TipoPlatilloRepository tipoPlatilloRepository;

    private final TipoPlatilloMapper tipoPlatilloMapper;

    public TipoPlatilloServiceImpl(TipoPlatilloRepository tipoPlatilloRepository, TipoPlatilloMapper tipoPlatilloMapper) {
        this.tipoPlatilloRepository = tipoPlatilloRepository;
        this.tipoPlatilloMapper = tipoPlatilloMapper;
    }

    @Override
    public TipoPlatilloDTO save(TipoPlatilloDTO tipoPlatilloDTO) {
        log.debug("Request to save TipoPlatillo : {}", tipoPlatilloDTO);
        TipoPlatillo tipoPlatillo = tipoPlatilloMapper.toEntity(tipoPlatilloDTO);
        tipoPlatillo = tipoPlatilloRepository.save(tipoPlatillo);
        return tipoPlatilloMapper.toDto(tipoPlatillo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoPlatilloDTO> findAll() {
        log.debug("Request to get all TipoPlatillos");
        return tipoPlatilloRepository.findAll().stream()
            .map(tipoPlatilloMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TipoPlatilloDTO> findOne(Long id) {
        log.debug("Request to get TipoPlatillo : {}", id);
        return tipoPlatilloRepository.findById(id)
            .map(tipoPlatilloMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoPlatillo : {}", id);
        tipoPlatilloRepository.deleteById(id);
    }
}
