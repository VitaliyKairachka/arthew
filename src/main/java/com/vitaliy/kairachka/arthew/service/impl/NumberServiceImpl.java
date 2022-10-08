package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.mapper.NumberMapper;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.repository.NumberRepository;
import com.vitaliy.kairachka.arthew.service.NumberService;
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
public class NumberServiceImpl implements NumberService {

  private final NumberRepository numberRepository;
  private final HotelRepository hotelRepository;
  private final NumberMapper numberMapper;

  @Override
  public List<NumberDto> getAllNumbers() {
    return numberRepository.findAll()
        .stream()
        .map(numberMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public NumberDto getNumberByName(String name) {
    var entity = numberRepository.findNumberByName(name);
    return numberMapper.toDtoFromEntity(entity);
  }

  @Override
  @Transactional
  public NumberDto createNumber(CreateNumberRequest request) {
    var numberDto = numberMapper.toDtoFromRequest(request);
    var entity = numberMapper.toEntityFromDto(numberDto);
    var hotelDto = numberDto.getHotel();
    if (hotelDto != null) {
      var hotel = hotelRepository.findById(hotelDto.getId());
      hotel.ifPresent(entity::setHotel);
    }
    return numberMapper.toDtoFromEntity(numberRepository.save(entity));
  }

  @Override
  @Transactional
  public NumberDto updateNumber(Long id, NumberDto numberDto) {
    var target = numberRepository.findById(id);
    if (target.isPresent()) {
      var update = numberMapper.toEntityFromDto(numberMapper.merge(numberDto, target.get()));
      return numberMapper.toDtoFromEntity(numberRepository.save(update));
    } else {
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
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
