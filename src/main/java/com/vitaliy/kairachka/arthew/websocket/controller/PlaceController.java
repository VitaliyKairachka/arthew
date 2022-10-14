package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PhotoResponse;
import com.vitaliy.kairachka.arthew.model.dto.response.PlaceResponse;
import com.vitaliy.kairachka.arthew.service.PhotoService;
import com.vitaliy.kairachka.arthew.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final PhotoService photoService;

    @MessageMapping("/place")
    @SendTo("/topic/place")
    public List<PlaceResponse> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return placeService.getAllPlaces(pageable);
    }

    @MessageMapping("/place/id/{id}")
    @SendTo("/topic/place")
    public PlaceResponse getById(@DestinationVariable Long id) {
        return placeService.getPlaceById(id);
    }

    @MessageMapping("/place/name/{name}")
    @SendTo("/topic/place")
    public PlaceResponse getByName(@DestinationVariable String name) {
        return placeService.getPlaceByName(name);
    }

    @MessageMapping("/place/create")
    @SendTo("/topic/place")
    public PlaceResponse create(@Payload CreatePlaceRequest request) {
        return placeService.createPlace(request);
    }

    @MessageMapping("/place/update/{id}")
    @SendTo("/topic/place")
    public PlaceResponse update(@DestinationVariable Long id, @Payload PlaceDto placeDto) {
        return placeService.updatePlace(id, placeDto);
    }

    @MessageMapping("/place/delete/{id}")
    @SendTo("/topic/place")
    public void delete(@DestinationVariable Long id) {
        placeService.deletePlace(id);
    }

    @MessageMapping("/place/photo")
    @SendTo("/topic/place")
    public List<PhotoResponse> getAllPhoto(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return photoService.getAllPhotos(pageable);
    }

    @MessageMapping("/place/photo/create")
    @SendTo("/topic/place")
    public PhotoResponse createPhoto(@Payload CreatePhotoRequest requests) {
        return photoService.createPhoto(requests);
    }

    @MessageMapping("/place/photo/delete/{id}")
    @SendTo("/topic/place")
    public void deletePhoto(@DestinationVariable UUID id) {
        photoService.deletePhoto(id);
    }
}
