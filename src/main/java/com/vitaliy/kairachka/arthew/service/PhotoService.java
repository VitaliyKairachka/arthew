package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PhotoResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author Vitaliy Kayrachka
 */
public interface PhotoService {
    List<PhotoResponse> getAllPhotos(Pageable pageable);

    PhotoResponse createPhoto(CreatePhotoRequest requestList);

    void deletePhoto(UUID id);
}
