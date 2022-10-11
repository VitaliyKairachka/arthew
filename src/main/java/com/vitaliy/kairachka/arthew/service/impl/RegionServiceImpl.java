package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.RegionResponse;
import com.vitaliy.kairachka.arthew.model.mapper.RegionMapper;
import com.vitaliy.kairachka.arthew.repository.CountryRepository;
import com.vitaliy.kairachka.arthew.repository.RegionRepository;
import com.vitaliy.kairachka.arthew.service.RegionService;
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
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;
    private final RegionMapper regionMapper;


    @Override
    @Cacheable(value = "regions")
    public List<RegionResponse> getAllRegions(Pageable pageable) {
        log.info("Get all regions");
        var list = regionRepository.findAll(pageable).toList();
        return list
                .stream()
                .map(regionMapper::toResponseFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "regions")
    public RegionResponse getRegionById(Long id) {
        var entity = regionRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get region with id: {}", id);
            return regionMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Region not found with id: {}", id);
            return new RegionResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Cacheable(value = "regions")
    public RegionResponse getRegionByName(String name) {
        var entity = regionRepository.findRegionByName(name);
        if (entity.isPresent()) {
            log.info("Get region with name: {}", name);
            return regionMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Region not found with name: {}", name);
            return new RegionResponse()
                    .setName(name)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "regions", allEntries = true)
    public RegionResponse createRegion(CreateRegionRequest request) {
        var regionDto = regionMapper.toDtoFromRequest(request);
        var entity = regionMapper.toEntityFromDto(regionDto);
        var countryDto = regionDto.getCountry();
        if (countryDto != null) {
            var country = countryRepository.findById(countryDto.getId());
            country.ifPresent(entity::setCountry);
        }
        log.info("Create new region with name: {}", entity.getName());
        return regionMapper.toResponseFromEntity(regionRepository.save(entity));
    }

    @Override
    @Transactional
    @CacheEvict(value = "regions", allEntries = true)
    public RegionResponse updateRegion(Long id, RegionDto regionDto) {
        var target = regionRepository.findById(id);
        if (target.isPresent()) {
            var update = regionMapper.toEntityFromDto(regionMapper.merge(regionDto, target.get()));
            return regionMapper.toResponseFromEntity(regionRepository.save(update));
        } else {
            log.info("Region not found with id: {}", id);
            return new RegionResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "regions", allEntries = true)
    public void deleteRegion(Long id) {
        var target = regionRepository.findById(id);
        if (target.isPresent()) {
            regionRepository.delete(target.get());
            log.info("Region deleted with id: {}", id);
        } else {
            log.info("Region not found with id: {}", id);
        }
    }
}
