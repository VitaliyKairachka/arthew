package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.HotelResponse;
import com.vitaliy.kairachka.arthew.model.mapper.HotelMapper;
import com.vitaliy.kairachka.arthew.repository.CountryRepository;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
import com.vitaliy.kairachka.arthew.repository.RegionRepository;
import com.vitaliy.kairachka.arthew.service.HotelService;
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
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final PlaceRepository placeRepository;
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;
    private final HotelMapper hotelMapper;

    @Override
    @Cacheable(value = "hotels")
    public List<HotelResponse> getAllHotels(Pageable pageable) {
        log.info("Get all hotels");
        var list = hotelRepository.findAll(pageable).toList();
        return list
                .stream()
                .map(hotelMapper::toResponseFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "hotels")
    public HotelResponse getHotelById(Long id) {
        var entity = hotelRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get hotel with id: {}", id);
            return hotelMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Hotel not found with id: {}", id);
            return new HotelResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Cacheable(value = "hotels")
    public HotelResponse getHotelByName(String name) {
        var entity = hotelRepository.findHotelByName(name);
        if (entity.isPresent()) {
            log.info("Get hotel with name: {}", name);
            return hotelMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Hotel not found with name: {}", name);
            return new HotelResponse()
                    .setName(name)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "hotels", allEntries = true)
    public HotelResponse createHotel(CreateHotelRequest request) {
        var hotelDto = hotelMapper.toDtoFromRequest(request);
        var entity = hotelMapper.toEntityFromDto(hotelDto);
        var placeDto = hotelDto.getPlace();
        if (placeDto != null) {
            var place = placeRepository.findById(placeDto.getId());
            place.ifPresent(placePresent -> {
                entity.setPlace(placePresent);
                var placeTmp = placePresent.setHotelCount(place.get().getHotelCount() + 1);
                placeRepository.save(placeTmp);

                var region = regionRepository.findById(placeTmp.getRegion().getId());
                region.ifPresent(regionPresent -> {
                    var regionTmp = regionPresent.setHotelCount(regionPresent.getHotelCount() + 1);
                    regionRepository.save(regionTmp);

                    var country = countryRepository.findById(regionTmp.getCountry().getId());
                    country.ifPresent(countryPresent -> {
                        var countryTmp = countryPresent.setHotelCounter(country.get().getHotelCounter() + 1);
                        countryRepository.save(countryTmp);
                    });
                });
            });
        }
        log.info("Create new hotel with name: {}", entity.getName());
        return hotelMapper.toResponseFromEntity(hotelRepository.save(entity));
    }

    @Override
    @Transactional
    @CacheEvict(value = "hotels", allEntries = true)
    public HotelResponse updateHotel(Long id, HotelDto hotelDto) {
        var target = hotelRepository.findById(id);
        if (target.isPresent()) {
            var update = hotelMapper.toEntityFromDto(hotelMapper.merge(hotelDto, target.get()));
            log.info("Hotel update with id: {}", id);
            return hotelMapper.toResponseFromEntity(hotelRepository.save(update));
        } else {
            log.info("Hotel not found with id: {}", id);
            return new HotelResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "hotels", allEntries = true)
    public void deleteHotel(Long id) {
        var target = hotelRepository.findById(id);
        if (target.isPresent()) {
            var place = placeRepository.findById(target.get().getPlace().getId());
            place.ifPresent(placePresent -> {
                placePresent.setHotelCount(placePresent.getHotelCount() - 1);
                placeRepository.save(placePresent);

                var region = regionRepository.findById(placePresent.getRegion().getId());
                region.ifPresent(regionPresent -> {
                    regionPresent.setHotelCount(regionPresent.getHotelCount() - 1);
                    regionRepository.save(regionPresent);

                    var country = countryRepository.findById(regionPresent.getCountry().getId());
                    country.ifPresent(countryPresent -> {
                        countryPresent.setHotelCounter(countryPresent.getHotelCounter() - 1);
                        countryRepository.save(countryPresent);
                    });
                });
            });
            hotelRepository.delete(target.get());
            log.info("Hotel deleted with id: {}", id);
        } else {
            log.info("Hotel not found with id: {}", id);
        }
    }
}
