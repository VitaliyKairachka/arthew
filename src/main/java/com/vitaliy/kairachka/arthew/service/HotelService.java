package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface HotelService {

  List<HotelDto> getAllHotels();

  HotelDto getHotelByName(String name);

  HotelDto createHotel(CreateHotelRequest request);

  HotelDto updateHotel(Long id, HotelDto hotelDto);

  void deleteHotel(Long id);
}
