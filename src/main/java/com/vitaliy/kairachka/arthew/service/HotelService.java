package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface HotelService {

  List<HotelDto> getAllHotels();

  HotelDto getHotelByName(String name);

  HotelDto createHotel(HotelDto hotelDto);

  HotelDto updateHotel(Long id, HotelDto hotelDto);

  void deleteHotel(Long id);
}
