package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.mapper.PlaceMapper;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
import com.vitaliy.kairachka.arthew.repository.RegionRepository;
import com.vitaliy.kairachka.arthew.service.PlaceService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public List<PlaceDto> getAllPlaces() {
    return placeRepository.findAll()
        .stream()
        .map(placeMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public PlaceDto getPlaceByName(String name) {
    var entity = placeRepository.findPlaceByName(name);
    return placeMapper.toDtoFromEntity(entity);
  }

  @Override
  @Transactional
  public PlaceDto createPlace(CreatePlaceRequest request) {
    var placeDto = placeMapper.toDtoFromRequest(request);
    var entity = placeMapper.toEntityFromDto(placeDto);
    var regionDto = placeDto.getRegion();
    if (regionDto != null) {
      var region = regionRepository.findById(regionDto.getId());
      region.ifPresent(entity::setRegion);
    }
    return placeMapper.toDtoFromEntity(placeRepository.save(entity));
  }

  @Override
  @Transactional
  public PlaceDto updatePlace(Long id, PlaceDto placeDto) {
    var target = placeRepository.findById(id);
    if (target.isPresent()) {
      var update = placeMapper.toEntityFromDto(placeMapper.merge(placeDto, target.get()));
      return placeMapper.toDtoFromEntity(placeRepository.save(update));
    } else {
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
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
