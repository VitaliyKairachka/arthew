package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface HotelService {

  Page<Hotel> getAllHotels(Pageable pageable);

  HotelDto getHotelById(Long id);

  HotelDto getHotelByName(String name);

  HotelDto createHotel(CreateHotelRequest request);

  HotelDto updateHotel(Long id, HotelDto hotelDto);

  void deleteHotel(Long id);
}
