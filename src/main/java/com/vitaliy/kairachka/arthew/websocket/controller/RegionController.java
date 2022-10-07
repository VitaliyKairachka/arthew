package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class RegionController {
    private final RegionService regionService;
}
