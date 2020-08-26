package com.microservice.food.service.impl;

import com.microservice.food.service.ComplementoService;
import com.microservice.food.domain.Complemento;
import com.microservice.food.repository.ComplementoRepository;
import com.microservice.food.service.dto.ComplementoDTO;
import com.microservice.food.service.mapper.ComplementoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Complemento}.
 */
@Service
@Transactional
public class ComplementoServiceImpl implements ComplementoService {

    private final Logger log = LoggerFactory.getLogger(ComplementoServiceImpl.class);

    private final ComplementoRepository complementoRepository;

    private final ComplementoMapper complementoMapper;

    public ComplementoServiceImpl(ComplementoRepository complementoRepository, ComplementoMapper complementoMapper) {
        this.complementoRepository = complementoRepository;
        this.complementoMapper = complementoMapper;
    }

    @Override
    public ComplementoDTO save(ComplementoDTO complementoDTO) {
        log.debug("Request to save Complemento : {}", complementoDTO);
        Complemento complemento = complementoMapper.toEntity(complementoDTO);
        complemento = complementoRepository.save(complemento);
        return complementoMapper.toDto(complemento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplementoDTO> findAll() {
        log.debug("Request to get all Complementos");
        return complementoRepository.findAll().stream()
            .map(complementoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ComplementoDTO> findOne(Long id) {
        log.debug("Request to get Complemento : {}", id);
        return complementoRepository.findById(id)
            .map(complementoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Complemento : {}", id);
        complementoRepository.deleteById(id);
    }
}
