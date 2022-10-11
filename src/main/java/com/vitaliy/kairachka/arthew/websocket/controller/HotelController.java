package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.PhotoDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import com.vitaliy.kairachka.arthew.model.entity.Photo;
import com.vitaliy.kairachka.arthew.service.HotelService;
import com.vitaliy.kairachka.arthew.service.PhotoService;
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
public class HotelController {

    private final HotelService hotelService;
    private final PhotoService photoService;

    @MessageMapping("/hotel")
    @SendTo("/topic/messages")
    public List<Hotel> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return hotelService.getAllHotels(pageable);
    }

    @MessageMapping("/hotel/{id}")
    @SendTo("/topic/messages")
    public HotelDto getById(@DestinationVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @MessageMapping("/hotel/{name}")
    @SendTo("/topic/messages")
    public HotelDto getByName(@DestinationVariable String name) {
        return hotelService.getHotelByName(name);
    }

    @MessageMapping("/hotel/create")
    @SendTo("/topic/messages")
    public HotelDto create(@Payload CreateHotelRequest request) {
        return hotelService.createHotel(request);
    }

    @MessageMapping("/hotel/update/{id}")
    @SendTo("/topic/messages")
    public HotelDto update(@DestinationVariable Long id, @Payload HotelDto hotelDto) {
        return hotelService.updateHotel(id, hotelDto);
    }

    @MessageMapping("/hotel/delete/{id}")
    @SendTo("/topic/messages")
    public void delete(@DestinationVariable Long id) {
        hotelService.deleteHotel(id);
    }

    @MessageMapping("/hotel/photo")
    @SendTo("/topic/messages")
    public List<Photo> getAllPhoto(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return photoService.getAllPhotos(pageable);
    }

    @MessageMapping("/hotel/photo/create")
    @SendTo("/topic/messages")
    public List<PhotoDto> createPhoto(@Payload List<CreatePhotoRequest> requests) {
        return photoService.createPhoto(requests);
    }

    @MessageMapping("/hotel/photo/delete/{id}")
    @SendTo("/topic/messages")
    public void deletePhoto(@DestinationVariable UUID id) {
        photoService.deletePhoto(id);
    }
}
