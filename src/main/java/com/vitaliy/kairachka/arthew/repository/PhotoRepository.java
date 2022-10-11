package com.vitaliy.kairachka.arthew.repository;

import com.vitaliy.kairachka.arthew.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

/**
 * @author Vitaliy Kayrachka
 */
public interface PhotoRepository extends JpaRepository<Photo, UUID>, JpaSpecificationExecutor<Photo> {
}
