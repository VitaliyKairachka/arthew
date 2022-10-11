package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.entity.Place;
import com.vitaliy.kairachka.arthew.model.mapper.PlaceMapper;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
import com.vitaliy.kairachka.arthew.repository.RegionRepository;
import com.vitaliy.kairachka.arthew.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final RegionRepository regionRepository;
    private final PlaceMapper placeMapper;

    @Override
    @Cacheable(value = "places")
    public List<Place> getAllPlaces(Pageable pageable) {
        log.info("Get all places");
        return placeRepository.findAll(pageable).toList();
    }

    @Override
    @Cacheable(value = "places")
    public PlaceDto getPlaceById(Long id) {
        var entity = placeRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get place with id: {}", id);
            return placeMapper.toDtoFromEntity(entity.get());
        } else {
            log.info("Place not found with id: {}", id);
            throw new RuntimeException(); //TODO
        }
    }

    @Override
    @Cacheable(value = "places")
    public PlaceDto getPlaceByName(String name) {
        var entity = placeRepository.findPlaceByName(name);
        if (entity.isPresent()) {
            log.info("Get place with name: {}", name);
            return placeMapper.toDtoFromEntity(entity.get());
        } else {
            log.info("Place not found with name: {}", name);
            throw new RuntimeException(); //TODO
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "places", allEntries = true)
    public PlaceDto createPlace(CreatePlaceRequest request) {
        var placeDto = placeMapper.toDtoFromRequest(request);
        var entity = placeMapper.toEntityFromDto(placeDto);
        var regionDto = placeDto.getRegion();
        if (regionDto != null) {
            var region = regionRepository.findById(regionDto.getId());
            region.ifPresent(entity::setRegion);
        }
        log.info("Create new place with name: {}", entity.getName());
        return placeMapper.toDtoFromEntity(placeRepository.save(entity));
    }

    @Override
    @Transactional
    @CacheEvict(value = "places", allEntries = true)
    public PlaceDto updatePlace(Long id, PlaceDto placeDto) {
        var target = placeRepository.findById(id);
        if (target.isPresent()) {
            var update = placeMapper.toEntityFromDto(placeMapper.merge(placeDto, target.get()));
            log.info("Place update with id: {}", id);
            return placeMapper.toDtoFromEntity(placeRepository.save(update));
        } else {
            log.info("Place not found with id: {}", id);
            throw new RuntimeException(); //TODO
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "places", allEntries = true)
    public void deletePlace(Long id) {
        var target = placeRepository.findById(id);
        if (target.isPresent()) {
            placeRepository.delete(target.get());
            log.info("Place deleted with id: {}", id);
        } else {
            log.info("Place not found with id: {}", id);
        }
    }
}
