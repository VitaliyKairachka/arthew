package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface PlaceService {

    Page<Place> getAllPlaces(Pageable pageable);

    PlaceDto getPlaceById(Long id);

    PlaceDto getPlaceByName(String name);

    PlaceDto createPlace(CreatePlaceRequest request);

    PlaceDto updatePlace(Long id, PlaceDto placeDto);

    void deletePlace(Long id);
}
