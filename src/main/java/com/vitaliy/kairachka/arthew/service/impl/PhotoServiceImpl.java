package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.PhotoDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.entity.Photo;
import com.vitaliy.kairachka.arthew.model.mapper.PhotoMapper;
import com.vitaliy.kairachka.arthew.repository.PhotoRepository;
import com.vitaliy.kairachka.arthew.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;

    @Override
    @Cacheable(value = "photos")
    public Page<Photo> getAllPhotos(Pageable pageable) {
        log.info("Get all photos");
        return photoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    @CacheEvict(value = "photos", allEntries = true)
    public List<PhotoDto> createPhoto(List<CreatePhotoRequest> requestList) {
        List<PhotoDto> responseList = new ArrayList<>();
        requestList.forEach(request -> {
            var photoDto = photoMapper.toDtoFromRequest(request);
            var entity = photoMapper.toEntityFromDto(photoDto);
            log.info("Create new photo with uuid: {}", entity.getId());
            responseList.add(photoMapper.toDtoFromEntity(photoRepository.save(entity)));
        });
        return responseList;
    }

    @Override
    @Transactional
    @CacheEvict(value = "photos", allEntries = true)
    public void deletePhoto(UUID id) {
        var target = photoRepository.findById(id);
        if (target.isPresent()) {
            photoRepository.delete(target.get());
            log.info("Photo deleted with uuid: {}", id);
        } else {
            log.info("Photo not found with uuid: {}", id);
        }
    }
}
