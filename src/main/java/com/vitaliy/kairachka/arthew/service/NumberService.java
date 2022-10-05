package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface NumberService {

  Page<NumberService> getAllNumbers(Pageable pageable);

  NumberDto getNumberByName(String name);

  NumberDto createNumber(NumberDto numberDto);

  NumberDto updateNumber(NumberDto numberDto);

  void deleteNumber(Long id);
}
