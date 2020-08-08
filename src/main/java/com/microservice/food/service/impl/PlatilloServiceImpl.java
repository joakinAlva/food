package com.microservice.food.service.impl;

import com.microservice.food.service.PlatilloService;
import com.microservice.food.domain.Platillo;
import com.microservice.food.repository.PlatilloRepository;
import com.microservice.food.service.dto.PlatilloDTO;
import com.microservice.food.service.mapper.PlatilloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Platillo}.
 */
@Service
@Transactional
public class PlatilloServiceImpl implements PlatilloService {

    private final Logger log = LoggerFactory.getLogger(PlatilloServiceImpl.class);

    private final PlatilloRepository platilloRepository;

    private final PlatilloMapper platilloMapper;

    public PlatilloServiceImpl(PlatilloRepository platilloRepository, PlatilloMapper platilloMapper) {
        this.platilloRepository = platilloRepository;
        this.platilloMapper = platilloMapper;
    }

    @Override
    public PlatilloDTO save(PlatilloDTO platilloDTO) {
        log.debug("Request to save Platillo : {}", platilloDTO);
        Platillo platillo = platilloMapper.toEntity(platilloDTO);
        platillo = platilloRepository.save(platillo);
        return platilloMapper.toDto(platillo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlatilloDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Platillos");
        return platilloRepository.findAll(pageable)
            .map(platilloMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PlatilloDTO> findOne(Long id) {
        log.debug("Request to get Platillo : {}", id);
        return platilloRepository.findById(id)
            .map(platilloMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Platillo : {}", id);
        platilloRepository.deleteById(id);
    }
}
