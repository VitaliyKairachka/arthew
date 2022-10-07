package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.mapper.PlaceMapper;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
import com.vitaliy.kairachka.arthew.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Vitaliy Kayrachka
 */
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

  private final PlaceRepository placeRepository;
  private final PlaceMapper placeMapper;

  @Override
  public Page<PlaceDto> getAllPlaces(Pageable pageable) {
    return null;
  }

  @Override
  public PlaceDto getPlaceByName(String name) {
    return null;
  }

  @Override
  public PlaceDto createPlace(PlaceDto placeDto) {
    return null;
  }

  @Override
  public PlaceDto updatePlace(PlaceDto placeDto) {
    return null;
  }

  @Override
  public void deletePlace(Long id) {

  }
}
