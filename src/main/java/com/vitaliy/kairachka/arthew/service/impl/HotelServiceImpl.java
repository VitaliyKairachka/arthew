package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import com.vitaliy.kairachka.arthew.model.mapper.HotelMapper;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
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
  private final PlaceRepository placeRepository;
  private final HotelMapper hotelMapper;

  @Override
  public List<HotelDto> getAllHotels() {
    return hotelRepository.findAll()
        .stream()
        .map(hotelMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public HotelDto getHotelById(Long id) {
    var entity = hotelRepository.findById(id);
    if (entity.isPresent()) {
      return hotelMapper.toDtoFromEntity(entity.get());
    } else {
      throw new RuntimeException();
    }
  }

  @Override
  public HotelDto getHotelByName(String name) {
    var entity = hotelRepository.findHotelByName(name);
    if (entity.isPresent()) {
      return hotelMapper.toDtoFromEntity(entity.get());
    } else {
      throw new RuntimeException();
    }
  }

  @Override
  @Transactional
  public HotelDto createHotel(CreateHotelRequest request) {
    var hotelDto = hotelMapper.toDtoFromRequest(request);
    var entity = hotelMapper.toEntityFromDto(hotelDto);
    var placeDto = hotelDto.getPlace();
    if (placeDto != null) {
      var place = placeRepository.findById(placeDto.getId());
      place.ifPresent(entity::setPlace);
    }
    return hotelMapper.toDtoFromEntity(hotelRepository.save(entity));
  }

  @Override
  @Transactional
  public HotelDto updateHotel(Long id, HotelDto hotelDto) {
    var target = hotelRepository.findById(id);
    if (target.isPresent()) {
      var update = hotelMapper.toEntityFromDto(hotelMapper.merge(hotelDto, target.get()));
      return hotelMapper.toDtoFromEntity(hotelRepository.save(update));
    } else {
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  public void deleteHotel(Long id) {
    var target = hotelRepository.findById(id);
    if (target.isPresent()) {
      hotelRepository.delete(target.get());
      log.info("Hotel deleted with id: {}", id);
    } else {
      log.info("Hotel not found with id: {}", id);
    }
  }
}
