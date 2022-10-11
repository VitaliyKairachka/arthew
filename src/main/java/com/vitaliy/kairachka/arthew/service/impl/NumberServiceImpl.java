package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.NumberResponse;
import com.vitaliy.kairachka.arthew.model.mapper.NumberMapper;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.repository.NumberRepository;
import com.vitaliy.kairachka.arthew.service.NumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<NumberResponse> getAllNumbers(Pageable pageable) {
        log.info("Get all numbers");
        var list = numberRepository.findAll(pageable).toList();
        return list
                .stream()
                .map(numberMapper::toResponseFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "numbers")
    public NumberResponse getNumberById(Long id) {
        var entity = numberRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get number with id: {}", id);
            return numberMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Number not found with id: {}", id);
            return new NumberResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "numbers", allEntries = true)
    public NumberResponse createNumber(CreateNumberRequest request) {
        var numberDto = numberMapper.toDtoFromRequest(request);
        var entity = numberMapper.toEntityFromDto(numberDto);
        var hotelEntity = numberDto.getHotel();
        if (hotelEntity != null) {
            var hotel = hotelRepository.findById(hotelEntity.getId());
            hotel.ifPresent(value -> {
                entity.setHotel(value);
                var tmp = value.setNumberCount(hotel.get().getNumberCount() + 1);
                hotelRepository.save(tmp);
            });
        }
        log.info("Create new number");
        return numberMapper.toResponseFromEntity(numberRepository.save(entity));
    }

    @Override
    @Transactional
    @CacheEvict(value = "numbers", allEntries = true)
    public NumberResponse updateNumber(Long id, NumberDto numberDto) {
        var target = numberRepository.findById(id);
        if (target.isPresent()) {
            var update = numberMapper.toEntityFromDto(numberMapper.merge(numberDto, target.get()));
            log.info("Number update with id: {}", id);
            return numberMapper.toResponseFromEntity(numberRepository.save(update));
        } else {
            log.info("Number not found with id: {}", id);
            return new NumberResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "numbers", allEntries = true)
    public void deleteNumber(Long id) {
        var target = numberRepository.findById(id);
        if (target.isPresent()) {
            var hotel = hotelRepository.findById(target.get().getHotel().getId());
            hotel.ifPresent(value -> {
                var tmp = value.setNumberCount(value.getNumberCount() - 1);
                hotelRepository.save(tmp);
            });
            numberRepository.delete(target.get());
            log.info("Number deleted with id: {}", id);
        } else {
            log.info("Number not found with id: {}", id);
        }
    }
}
