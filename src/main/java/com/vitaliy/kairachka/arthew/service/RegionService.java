package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface RegionService {

  Page<RegionDto> getAllRegions(Pageable pageable);

  RegionDto getRegionByName(String name);

  RegionDto createRegion(RegionDto regionDto);

  RegionDto updateRegion(RegionDto regionDto);

  void deleteRegion(Long id);
}
