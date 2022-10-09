package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import com.vitaliy.kairachka.arthew.model.mapper.HotelMapper;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
import com.vitaliy.kairachka.arthew.service.HotelService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  @Cacheable(value = "hotels")
  public Page<Hotel> getAllHotels(Pageable pageable) {
    log.info("Get all hotels");
    return hotelRepository.findAll(pageable);
  }

  @Override
  @Cacheable(value = "hotels")
  public HotelDto getHotelById(Long id) {
    var entity = hotelRepository.findById(id);
    if (entity.isPresent()) {
      log.info("Get hotel with id: {}", id);
      return hotelMapper.toDtoFromEntity(entity.get());
    } else {
      log.info("Hotel not found with id: {}", id);
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Cacheable(value = "hotels")
  public HotelDto getHotelByName(String name) {
    var entity = hotelRepository.findHotelByName(name);
    if (entity.isPresent()) {
      log.info("Get hotel with name: {}", name);
      return hotelMapper.toDtoFromEntity(entity.get());
    } else {
      log.info("Hotel not found with name: {}", name);
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = "hotels", allEntries = true)
  public HotelDto createHotel(CreateHotelRequest request) {
    var hotelDto = hotelMapper.toDtoFromRequest(request);
    var entity = hotelMapper.toEntityFromDto(hotelDto);
    var placeDto = hotelDto.getPlace();
    if (placeDto != null) {
      var place = placeRepository.findById(placeDto.getId());
      place.ifPresent(entity::setPlace);
    }
    log.info("Create new hotel with name: {}", entity.getName());
    return hotelMapper.toDtoFromEntity(hotelRepository.save(entity));
  }

  @Override
  @Transactional
  @CacheEvict(value = "hotels", allEntries = true)
  public HotelDto updateHotel(Long id, HotelDto hotelDto) {
    var target = hotelRepository.findById(id);
    if (target.isPresent()) {
      var update = hotelMapper.toEntityFromDto(hotelMapper.merge(hotelDto, target.get()));
      log.info("Hotel update with id: {}", id);
      return hotelMapper.toDtoFromEntity(hotelRepository.save(update));
    } else {
      log.info("Hotel not found with id: {}", id);
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = "hotels", allEntries = true)
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
