package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface NumberService {

  List<NumberDto> getAllNumbers();

  NumberDto getNumberByName(String name);

  NumberDto createNumber(CreateNumberRequest request);

  NumberDto updateNumber(Long id, NumberDto numberDto);

  void deleteNumber(Long id);
}
