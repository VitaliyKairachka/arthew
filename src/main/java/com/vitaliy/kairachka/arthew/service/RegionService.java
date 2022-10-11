package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import com.vitaliy.kairachka.arthew.model.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface RegionService {

    List<Region> getAllRegions(Pageable pageable);

    RegionDto getRegionById(Long id);

    RegionDto getRegionByName(String name);

    RegionDto createRegion(CreateRegionRequest request);

    RegionDto updateRegion(Long id, RegionDto regionDto);

    void deleteRegion(Long id);
}
