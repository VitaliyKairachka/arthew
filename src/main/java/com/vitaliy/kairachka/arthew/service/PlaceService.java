package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PlaceResponse;
import com.vitaliy.kairachka.arthew.model.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface PlaceService {

    List<PlaceResponse> getAllPlaces(Pageable pageable);

    PlaceResponse getPlaceById(Long id);

    PlaceResponse getPlaceByName(String name);

    PlaceResponse createPlace(CreatePlaceRequest request);

    PlaceResponse updatePlace(Long id, PlaceDto placeDto);

    void deletePlace(Long id);
}
