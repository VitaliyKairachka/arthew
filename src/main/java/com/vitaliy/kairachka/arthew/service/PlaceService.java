package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface PlaceService {

  Page<PlaceDto> getAllPlaces(Pageable pageable);

  PlaceDto getPlaceByName(String name);

  PlaceDto createPlace(PlaceDto placeDto);

  PlaceDto updatePlace(PlaceDto placeDto);

  void deletePlace(Long id);
}
