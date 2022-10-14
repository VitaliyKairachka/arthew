package com.vitaliy.kairachka.arthew.repository;

import com.vitaliy.kairachka.arthew.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Vitaliy Kayrachka
 */
public interface RegionRepository extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region> {

    Optional<Region> findRegionByName(String name);
}
