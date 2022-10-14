package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PhotoResponse;
import com.vitaliy.kairachka.arthew.model.mapper.PhotoMapper;
import com.vitaliy.kairachka.arthew.repository.HotelRepository;
import com.vitaliy.kairachka.arthew.repository.NumberRepository;
import com.vitaliy.kairachka.arthew.repository.PhotoRepository;
import com.vitaliy.kairachka.arthew.repository.PlaceRepository;
import com.vitaliy.kairachka.arthew.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final PlaceRepository placeRepository;
    private final HotelRepository hotelRepository;
    private final NumberRepository numberRepository;
    private final PhotoMapper photoMapper;

    @Override
    @Cacheable(value = "photos")
    public List<PhotoResponse> getAllPhotos(Pageable pageable) {
        log.info("Get all photos");
        var list = photoRepository.findAll(pageable).toList();
        return list
                .stream()
                .map(photoMapper::toResponseFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = {"photos", "places", "hotels", "numbers"}, allEntries = true)
    public PhotoResponse createPhoto(CreatePhotoRequest request) {
        var photoDto = photoMapper.toDtoFromRequest(request);

        if (photoDto.getPlace().getId() != null) {
            var placeEntity = placeRepository.findById(photoDto.getPlace().getId());
            placeEntity.ifPresent(place -> {
                place.setPhotoCount(place.getPhotoCount() + 1);
                placeRepository.save(place);
            });
        } else if (photoDto.getHotel().getId() != null) {
            var hotelEntity = hotelRepository.findById(photoDto.getHotel().getId());
            hotelEntity.ifPresent(hotel -> {
                hotel.setPhotoCount(hotel.getPhotoCount() + 1);
                hotelRepository.save(hotel);
            });
        } else if (photoDto.getNumber().getId() != null) {
            var numberEntity = numberRepository.findById(photoDto.getNumber().getId());
            numberEntity.ifPresent(number -> {
                number.setPhotoCount(number.getPhotoCount() + 1);
                numberRepository.save(number);
            });
        }

        var entity = photoMapper.toEntityFromDto(photoDto);
        log.info("Create new photo with uuid: {}", entity.getId());
        return photoMapper.toResponseFromEntity(photoRepository.save(entity));

    }

    @Override
    @Transactional
    @CacheEvict(value = {"photos", "places", "hotels", "numbers"}, allEntries = true)
    public void deletePhoto(UUID id) {
        var target = photoRepository.findById(id);
        if (target.isPresent()) {
            photoRepository.delete(target.get());

            if (target.get().getPlace().getId() != 0) {
                var placeEntity = placeRepository.findById(target.get().getPlace().getId());
                placeEntity.ifPresent(place -> {
                    place.setPhotoCount(place.getPhotoCount() - 1);
                    placeRepository.save(place);
                });
            } else if (target.get().getHotel().getId() != 0) {
                var hotelEntity = hotelRepository.findById(target.get().getHotel().getId());
                hotelEntity.ifPresent(hotel -> {
                    hotel.setPhotoCount(hotel.getPhotoCount() - 1);
                    hotelRepository.save(hotel);
                });
            } else if (target.get().getNumber().getId() != 0) {
                var numberEntity = numberRepository.findById(target.get().getNumber().getId());
                numberEntity.ifPresent(number -> {
                    number.setPhotoCount(number.getPhotoCount() - 1);
                    numberRepository.save(number);
                });

                log.info("Photo deleted with uuid: {}", id);
            } else {
                log.info("Photo not found with uuid: {}", id);
            }
        }
    }
}
