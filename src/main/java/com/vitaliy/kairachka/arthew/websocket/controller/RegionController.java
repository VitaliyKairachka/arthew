package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import com.vitaliy.kairachka.arthew.service.RegionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class RegionController {

  private final RegionService regionService;

  @MessageMapping("/region")
  @SendTo("/topic/messages")
  public List<RegionDto> getAll() {
    return regionService.getAllRegions();
  }

  @MessageMapping("/country/{id}")
  @SendTo("/topic/messages")
  public RegionDto getById(@DestinationVariable Long id) {
    return regionService.getRegionById(id);
  }

  @MessageMapping("/country/{name}")
  @SendTo("/topic/messages")
  public RegionDto getByName(@DestinationVariable String name) {
    return regionService.getRegionByName(name);
  }

  @MessageMapping("/country/create")
  @SendTo("/topic/messages")
  public RegionDto create(@Payload CreateRegionRequest request) {
    return regionService.createRegion(request);
  }

  @MessageMapping("/country/update/{id}")
  @SendTo("/topic/messages")
  public RegionDto update(@DestinationVariable Long id, @Payload RegionDto regionDto) {
    return regionService.updateRegion(id, regionDto);
  }

  @MessageMapping("/country/delete/{id}")
  @SendTo("/topic/messages")
  public void delete(@DestinationVariable Long id) {
    regionService.deleteRegion(id);
  }
}
