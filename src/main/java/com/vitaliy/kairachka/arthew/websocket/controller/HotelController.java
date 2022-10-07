package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class HotelController {
  private final HotelService hotelService;

}
