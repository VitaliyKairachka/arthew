package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.NumberResponse;
import com.vitaliy.kairachka.arthew.model.dto.response.PhotoResponse;
import com.vitaliy.kairachka.arthew.service.NumberService;
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
public class NumberController {

    private final NumberService numberService;
    private final PhotoService photoService;

    @MessageMapping("/number")
    @SendTo("/topic/number")
    public List<NumberResponse> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return numberService.getAllNumbers(pageable);
    }

    @MessageMapping("/number/id/{id}")
    @SendTo("/topic/number")
    public NumberResponse getById(@DestinationVariable Long id) {
        return numberService.getNumberById(id);
    }

    @MessageMapping("/number/create")
    @SendTo("/topic/number")
    public NumberResponse create(@Payload CreateNumberRequest request) {
        return numberService.createNumber(request);
    }

    @MessageMapping("/number/update/{id}")
    @SendTo("/topic/number")
    public NumberResponse update(@DestinationVariable Long id, @Payload NumberDto numberDto) {
        return numberService.updateNumber(id, numberDto);
    }

    @MessageMapping("/number/delete/{id}")
    @SendTo("/topic/number")
    public void delete(@DestinationVariable Long id) {
        numberService.deleteNumber(id);
    }

    @MessageMapping("/number/photo")
    @SendTo("/topic/number")
    public List<PhotoResponse> getAllPhoto(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return photoService.getAllPhotos(pageable);
    }

    @MessageMapping("/number/photo/create")
    @SendTo("/topic/number")
    public PhotoResponse createPhoto(@Payload CreatePhotoRequest requests) {
        return photoService.createPhoto(requests);
    }

    @MessageMapping("/number/photo/delete/{id}")
    @SendTo("/topic/number")
    public void deletePhoto(@DestinationVariable UUID id) {
        photoService.deletePhoto(id);
    }
}
