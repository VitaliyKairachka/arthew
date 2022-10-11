package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.RegionResponse;
import com.vitaliy.kairachka.arthew.service.RegionService;
import lombok.AllArgsConstructor;
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
    public List<RegionResponse> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return regionService.getAllRegions(pageable);
    }

    @MessageMapping("/region/{id}")
    @SendTo("/topic/region")
    public RegionResponse getById(@DestinationVariable Long id) {
        return regionService.getRegionById(id);
    }

    @MessageMapping("/region/{name}")
    @SendTo("/topic/region")
    public RegionResponse getByName(@DestinationVariable String name) {
        return regionService.getRegionByName(name);
    }

    @MessageMapping("/region/create")
    @SendTo("/topic/region")
    public RegionResponse create(@Payload CreateRegionRequest request) {
        return regionService.createRegion(request);
    }

    @MessageMapping("/region/update/{id}")
    @SendTo("/topic/region")
    public RegionResponse update(@DestinationVariable Long id, @Payload RegionDto regionDto) {
        return regionService.updateRegion(id, regionDto);
    }

    @MessageMapping("/region/delete/{id}")
    @SendTo("/topic/region")
    public void delete(@DestinationVariable Long id) {
        regionService.deleteRegion(id);
    }
}
