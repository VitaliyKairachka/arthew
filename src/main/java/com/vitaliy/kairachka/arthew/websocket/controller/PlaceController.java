package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.service.PlaceService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class PlaceController {

  private final PlaceService placeService;

  @MessageMapping("/place")
  @SendTo("/topic/messages")
  public List<PlaceDto> getAll() {
    return placeService.getAllPlaces();
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
}
