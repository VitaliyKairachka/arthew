package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PlaceResponse;
import com.vitaliy.kairachka.arthew.model.mapper.PlaceMapper;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
import com.vitaliy.kairachka.arthew.repository.RegionRepository;
import com.vitaliy.kairachka.arthew.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PlaceResponse> getAllPlaces(Pageable pageable) {
        log.info("Get all places");
        var list = placeRepository.findAll(pageable).toList();
        return list
                .stream()
                .map(placeMapper::toResponseFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "places")
    public PlaceResponse getPlaceById(Long id) {
        var entity = placeRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get place with id: {}", id);
            return placeMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Place not found with id: {}", id);
            return new PlaceResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Cacheable(value = "places")
    public PlaceResponse getPlaceByName(String name) {
        var entity = placeRepository.findPlaceByName(name);
        if (entity.isPresent()) {
            log.info("Get place with name: {}", name);
            return placeMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Place not found with name: {}", name);
            return new PlaceResponse()
                    .setName(name)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "places", allEntries = true)
    public PlaceResponse createPlace(CreatePlaceRequest request) {
        var placeDto = placeMapper.toDtoFromRequest(request);
        var entity = placeMapper.toEntityFromDto(placeDto);
        var regionDto = placeDto.getRegion();
        if (regionDto != null) {
            var region = regionRepository.findById(regionDto.getId());
            region.ifPresent(entity::setRegion);
        }
        log.info("Create new place with name: {}", entity.getName());
        return placeMapper.toResponseFromEntity(placeRepository.save(entity));
    }

    @Override
    @Transactional
    @CacheEvict(value = "places", allEntries = true)
    public PlaceResponse updatePlace(Long id, PlaceDto placeDto) {
        var target = placeRepository.findById(id);
        if (target.isPresent()) {
            var update = placeMapper.toEntityFromDto(placeMapper.merge(placeDto, target.get()));
            log.info("Place update with id: {}", id);
            return placeMapper.toResponseFromEntity(placeRepository.save(update));
        } else {
            log.info("Place not found with id: {}", id);
            return new PlaceResponse()
                    .setId(id)
                    .setIsFound(false);
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
