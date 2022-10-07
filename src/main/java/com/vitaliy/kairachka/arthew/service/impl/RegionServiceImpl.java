package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.mapper.RegionMapper;
import com.vitaliy.kairachka.arthew.repository.RegionRepository;
import com.vitaliy.kairachka.arthew.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Vitaliy Kayrachka
 */
@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

  private final RegionRepository regionRepository;
  private final RegionMapper regionMapper;

  @Override
  public Page<RegionDto> getAllRegions(Pageable pageable) {
    return null;
  }

  @Override
  public RegionDto getRegionByName(String name) {
    return null;
  }

  @Override
  public RegionDto createRegion(RegionDto regionDto) {
    return null;
  }

  @Override
  public RegionDto updateRegion(RegionDto regionDto) {
    return null;
  }

  @Override
  public void deleteRegion(Long id) {

  }
}
