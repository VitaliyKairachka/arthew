package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.HotelResponse;
import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface HotelService {

    List<HotelResponse> getAllHotels(Pageable pageable);

    HotelResponse getHotelById(Long id);

    HotelResponse getHotelByName(String name);

    HotelResponse createHotel(CreateHotelRequest request);

    HotelResponse updateHotel(Long id, HotelDto hotelDto);

    void deleteHotel(Long id);
}
