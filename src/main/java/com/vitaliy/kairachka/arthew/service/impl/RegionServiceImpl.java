package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import com.vitaliy.kairachka.arthew.model.mapper.RegionMapper;
import com.vitaliy.kairachka.arthew.repository.CountryRepository;
import com.vitaliy.kairachka.arthew.repository.RegionRepository;
import com.vitaliy.kairachka.arthew.service.RegionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public List<RegionDto> getAllRegions() {
    log.info("Get all regions");
    return regionRepository.findAll()
        .stream()
        .map(regionMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  @Cacheable(value = "regions")
  public RegionDto getRegionById(Long id) {
    var entity = regionRepository.findById(id);
    if (entity.isPresent()) {
      log.info("Get region with id: {}", id);
      return regionMapper.toDtoFromEntity(entity.get());
    } else {
      log.info("Region not found with id: {}", id);
      throw new RuntimeException();  //TODO
    }
  }

  @Override
  @Cacheable(value = "regions")
  public RegionDto getRegionByName(String name) {
    var entity = regionRepository.findRegionByName(name);
    if (entity.isPresent()) {
      log.info("Get region with name: {}", name);
      return regionMapper.toDtoFromEntity(entity.get());
    } else {
      log.info("Region not found with name: {}", name);
      throw new RuntimeException();  //TODO
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = "regions", allEntries = true)
  public RegionDto createRegion(CreateRegionRequest request) {
    var regionDto = regionMapper.toDtoFromRequest(request);
    var entity = regionMapper.toEntityFromDto(regionDto);
    var countryDto = regionDto.getCountry();
    if (countryDto != null) {
      var country = countryRepository.findById(countryDto.getId());
      country.ifPresent(entity::setCountry);
    }
    log.info("Create new region with name: {}", entity.getName());
    return regionMapper.toDtoFromEntity(regionRepository.save(entity));
  }

  @Override
  @Transactional
  @CacheEvict(value = "regions", allEntries = true)
  public RegionDto updateRegion(Long id, RegionDto regionDto) {
    var target = regionRepository.findById(id);
    if (target.isPresent()) {
      var update = regionMapper.toEntityFromDto(regionMapper.merge(regionDto, target.get()));
      return regionMapper.toDtoFromEntity(regionRepository.save(update));
    } else {
      log.info("Region not found with id: {}", id);
      throw new RuntimeException(); //TODO
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
