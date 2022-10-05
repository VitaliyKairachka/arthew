package com.vitaliy.kairachka.arthew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Vitaliy Kayrachka
 */
public interface NumberRepository extends JpaRepository<Number, Long>, JpaSpecificationExecutor<Number> {

}
