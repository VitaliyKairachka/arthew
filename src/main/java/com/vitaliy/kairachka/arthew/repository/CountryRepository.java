package com.vitaliy.kairachka.arthew.repository;

import com.vitaliy.kairachka.arthew.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Vitaliy Kayrachka
 */
public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

  Country findCountryByName(String name);
}
