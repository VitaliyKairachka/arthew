package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import com.vitaliy.kairachka.arthew.model.entity.Region;
import com.vitaliy.kairachka.arthew.service.RegionService;
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

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @MessageMapping("/region")
    @SendTo("/topic/region")
    public List<Region> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return regionService.getAllRegions(pageable);
    }

    @MessageMapping("/region/{id}")
    @SendTo("/topic/region")
    public RegionDto getById(@DestinationVariable Long id) {
        return regionService.getRegionById(id);
    }

    @MessageMapping("/region/{name}")
    @SendTo("/topic/region")
    public RegionDto getByName(@DestinationVariable String name) {
        return regionService.getRegionByName(name);
    }

    @MessageMapping("/region/create")
    @SendTo("/topic/region")
    public RegionDto create(@Payload CreateRegionRequest request) {
        return regionService.createRegion(request);
    }

    @MessageMapping("/region/update/{id}")
    @SendTo("/topic/region")
    public RegionDto update(@DestinationVariable Long id, @Payload RegionDto regionDto) {
        return regionService.updateRegion(id, regionDto);
    }

    @MessageMapping("/region/delete/{id}")
    @SendTo("/topic/region")
    public void delete(@DestinationVariable Long id) {
        regionService.deleteRegion(id);
    }
}
