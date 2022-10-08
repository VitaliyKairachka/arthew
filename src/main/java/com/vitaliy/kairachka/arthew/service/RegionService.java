package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface RegionService {

  List<RegionDto> getAllRegions();

  RegionDto getRegionByName(String name);

  RegionDto createRegion(CreateRegionRequest request);

  RegionDto updateRegion(Long id, RegionDto regionDto);

  void deleteRegion(Long id);
}
