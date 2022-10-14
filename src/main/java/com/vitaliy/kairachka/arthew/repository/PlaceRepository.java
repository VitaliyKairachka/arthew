package com.vitaliy.kairachka.arthew.repository;

import com.vitaliy.kairachka.arthew.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Vitaliy Kayrachka
 */
public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {

    Optional<Place> findPlaceByName(String name);
}
