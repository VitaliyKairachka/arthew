package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.mapper.HotelMapper;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.service.HotelService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

  private final HotelRepository hotelRepository;
  private final HotelMapper hotelMapper;

  @Override
  public List<HotelDto> getAllHotels() {
    return hotelRepository.findAll()
        .stream()
        .map(hotelMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public HotelDto getHotelByName(String name) {
    var entity = hotelRepository.findHotelByName(name);
    return hotelMapper.toDtoFromEntity(entity);
  }

  @Override
  @Transactional
  public HotelDto createHotel(HotelDto hotelDto) {
    var entity = hotelMapper.toEntityFromDto(hotelDto);
    return hotelMapper.toDtoFromEntity(hotelRepository.save(entity));
  }

  @Override
  @Transactional
  public HotelDto updateHotel(Long id, HotelDto hotelDto) {
    var target = hotelRepository.findById(id);
    var update = hotelMapper.toEntityFromDto(hotelMapper.merge(hotelDto, target));
    return hotelMapper.toDtoFromEntity(hotelRepository.save(update));
  }

  @Override
  @Transactional
  public void deleteHotel(Long id) {

  }
}
