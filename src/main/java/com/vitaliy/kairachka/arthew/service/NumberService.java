package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.entity.Number;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface NumberService {

  Page<Number> getAllNumbers(Pageable pageable);

  NumberDto getNumberById(Long id);

  NumberDto createNumber(CreateNumberRequest request);

  NumberDto updateNumber(Long id, NumberDto numberDto);

  void deleteNumber(Long id);
}
