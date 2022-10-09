package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.entity.Number;
import com.vitaliy.kairachka.arthew.model.mapper.NumberMapper;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.repository.NumberRepository;
import com.vitaliy.kairachka.arthew.service.NumberService;
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
public class NumberServiceImpl implements NumberService {

  private final NumberRepository numberRepository;
  private final HotelRepository hotelRepository;
  private final NumberMapper numberMapper;

  @Override
  @Cacheable(value = "numbers")
  public Page<Number> getAllNumbers(Pageable pageable) {
    log.info("Get all numbers");
    return numberRepository.findAll(pageable);
  }

  @Override
  @Cacheable(value = "numbers")
  public NumberDto getNumberById(Long id) {
    var entity = numberRepository.findById(id);
    if (entity.isPresent()) {
      log.info("Get number with id: {}", id);
      return numberMapper.toDtoFromEntity(entity.get());
    } else {
      log.info("Number not found with id: {}", id);
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = "numbers", allEntries = true)
  public NumberDto createNumber(CreateNumberRequest request) {
    var numberDto = numberMapper.toDtoFromRequest(request);
    var entity = numberMapper.toEntityFromDto(numberDto);
    var hotelDto = numberDto.getHotel();
    if (hotelDto != null) {
      var hotel = hotelRepository.findById(hotelDto.getId());
      hotel.ifPresent(entity::setHotel);
    }
    log.info("Create new number");
    return numberMapper.toDtoFromEntity(numberRepository.save(entity));
  }

  @Override
  @Transactional
  @CacheEvict(value = "numbers", allEntries = true)
  public NumberDto updateNumber(Long id, NumberDto numberDto) {
    var target = numberRepository.findById(id);
    if (target.isPresent()) {
      var update = numberMapper.toEntityFromDto(numberMapper.merge(numberDto, target.get()));
      log.info("Number update with id: {}", id);
      return numberMapper.toDtoFromEntity(numberRepository.save(update));
    } else {
      log.info("Number not found with id: {}", id);
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = "numbers", allEntries = true)
  public void deleteNumber(Long id) {
    var target = numberRepository.findById(id);
    if (target.isPresent()) {
      numberRepository.delete(target.get());
      log.info("Number deleted with id: {}", id);
    } else {
      log.info("Number not found with id: {}", id);
    }
  }
}
