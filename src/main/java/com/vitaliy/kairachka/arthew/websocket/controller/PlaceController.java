package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.PhotoDto;
import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.entity.Photo;
import com.vitaliy.kairachka.arthew.model.entity.Place;
import com.vitaliy.kairachka.arthew.service.PhotoService;
import com.vitaliy.kairachka.arthew.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    @SendTo("/topic/messages")
    public List<Place> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return placeService.getAllPlaces(pageable);
    }

    @MessageMapping("/place/{id}")
    @SendTo("/topic/messages")
    public PlaceDto getById(@DestinationVariable Long id) {
        return placeService.getPlaceById(id);
    }

    @MessageMapping("/place/{name}")
    @SendTo("/topic/messages")
    public PlaceDto getByName(@DestinationVariable String name) {
        return placeService.getPlaceByName(name);
    }

    @MessageMapping("/place/create")
    @SendTo("/topic/messages")
    public PlaceDto create(@Payload CreatePlaceRequest request) {
        return placeService.createPlace(request);
    }

    @MessageMapping("/place/update/{id}")
    @SendTo("/topic/messages")
    public PlaceDto update(@DestinationVariable Long id, @Payload PlaceDto placeDto) {
        return placeService.updatePlace(id, placeDto);
    }

    @MessageMapping("/place/delete/{id}")
    @SendTo("/topic/messages")
    public void delete(@DestinationVariable Long id) {
        placeService.deletePlace(id);
    }

    @MessageMapping("/place/photo")
    @SendTo("/topic/messages")
    public List<Photo> getAllPhoto(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return photoService.getAllPhotos(pageable);
    }

    @MessageMapping("/place/photo/create")
    @SendTo("/topic/messages")
    public List<PhotoDto> createPhoto(@Payload List<CreatePhotoRequest> requests) {
        return photoService.createPhoto(requests);
    }

    @MessageMapping("/place/photo/delete/{id}")
    @SendTo("/topic/messages")
    public void deletePhoto(@DestinationVariable UUID id) {
        photoService.deletePhoto(id);
    }
}
