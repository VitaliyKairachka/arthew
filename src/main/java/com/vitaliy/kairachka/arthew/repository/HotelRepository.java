package com.vitaliy.kairachka.arthew.repository;

import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Vitaliy Kayrachka
 */
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

  Hotel findHotelByName(String name);
}
