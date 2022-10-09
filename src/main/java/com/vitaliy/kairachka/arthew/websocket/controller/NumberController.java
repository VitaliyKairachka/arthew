package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.entity.Number;
import com.vitaliy.kairachka.arthew.service.NumberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class NumberController {

  private final NumberService numberService;

  @MessageMapping("/number")
  @SendTo("/topic/messages")
  public Page<Number> getAll(@Payload Pageable pageable) {
    return numberService.getAllNumbers(pageable);
  }

  @MessageMapping("number/{id}")
  @SendTo("/topic/messages")
  public NumberDto getById(@DestinationVariable Long id) {
    return numberService.getNumberById(id);
  }

  @MessageMapping("number/create")
  @SendTo("/topic/messages")
  public NumberDto create(@Payload CreateNumberRequest request) {
    return numberService.createNumber(request);
  }

  @MessageMapping("number/update/{id}")
  @SendTo("/topic/messages")
  public NumberDto update(@DestinationVariable Long id, @Payload NumberDto numberDto) {
    return numberService.updateNumber(id, numberDto);
  }

  @MessageMapping("number/delete/{id}")
  @SendTo("/topic/messages")
  public void delete(@DestinationVariable Long id) {
    numberService.deleteNumber(id);
  }
}
