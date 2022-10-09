package com.vitaliy.kairachka.arthew.repository;

import com.vitaliy.kairachka.arthew.model.entity.Region;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Vitaliy Kayrachka
 */
public interface RegionRepository extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region> {

  Optional<Region> findRegionByName(String name);
}
