package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class PlaceController {
  private final PlaceService placeService;
}
