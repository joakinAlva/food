package com.microservice.food.service.impl;

import com.microservice.food.service.ExtraService;
import com.microservice.food.domain.Extra;
import com.microservice.food.repository.ExtraRepository;
import com.microservice.food.service.dto.ExtraDTO;
import com.microservice.food.service.mapper.ExtraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Extra}.
 */
@Service
@Transactional
public class ExtraServiceImpl implements ExtraService {

    private final Logger log = LoggerFactory.getLogger(ExtraServiceImpl.class);

    private final ExtraRepository extraRepository;

    private final ExtraMapper extraMapper;

    public ExtraServiceImpl(ExtraRepository extraRepository, ExtraMapper extraMapper) {
        this.extraRepository = extraRepository;
        this.extraMapper = extraMapper;
    }

    @Override
    public ExtraDTO save(ExtraDTO extraDTO) {
        log.debug("Request to save Extra : {}", extraDTO);
        Extra extra = extraMapper.toEntity(extraDTO);
        extra = extraRepository.save(extra);
        return extraMapper.toDto(extra);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExtraDTO> findAll() {
        log.debug("Request to get all Extras");
        return extraRepository.findAll().stream()
            .map(extraMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ExtraDTO> findOne(Long id) {
        log.debug("Request to get Extra : {}", id);
        return extraRepository.findById(id)
            .map(extraMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Extra : {}", id);
        extraRepository.deleteById(id);
    }
}
