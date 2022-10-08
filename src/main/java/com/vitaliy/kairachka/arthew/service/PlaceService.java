package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface PlaceService {

  List<PlaceDto> getAllPlaces();

  PlaceDto getPlaceByName(String name);

  PlaceDto createPlace(CreatePlaceRequest request);

  PlaceDto updatePlace(Long id, PlaceDto placeDto);

  void deletePlace(Long id);
}
