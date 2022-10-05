package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;

/**
 * @author Vitaliy Kayrachka
 */
public interface HotelService {

  Page<HotelDto> getAllHotels(Pageable pageable);

  HotelDto getHotelByName(String name);

  HotelDto createHotel(HotelDto hotelDto);

  HotelDto updateHotel(HotelDto hotelDto);

  void deleteHotel(Long id);
}
