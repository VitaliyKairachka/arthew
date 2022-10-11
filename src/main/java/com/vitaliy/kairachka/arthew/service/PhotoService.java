package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.PhotoDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PhotoResponse;
import com.vitaliy.kairachka.arthew.model.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author Vitaliy Kayrachka
 */
public interface PhotoService {
    List<PhotoResponse> getAllPhotos(Pageable pageable);

    List<PhotoResponse> createPhoto(List<CreatePhotoRequest> requestList);

    void deletePhoto(UUID id);
}
