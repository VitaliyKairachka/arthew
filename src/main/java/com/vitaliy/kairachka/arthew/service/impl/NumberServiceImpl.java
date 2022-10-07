package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.mapper.NumberMapper;
import com.vitaliy.kairachka.arthew.repository.NumberRepository;
import com.vitaliy.kairachka.arthew.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Vitaliy Kayrachka
 */
@Service
@RequiredArgsConstructor
public class NumberServiceImpl implements NumberService {

  private final NumberRepository numberRepository;
  private final NumberMapper numberMapper;

  @Override
  public Page<NumberService> getAllNumbers(Pageable pageable) {
    return null;
  }

  @Override
  public NumberDto getNumberByName(String name) {
    return null;
  }

  @Override
  public NumberDto createNumber(NumberDto numberDto) {
    return null;
  }

  @Override
  public NumberDto updateNumber(NumberDto numberDto) {
    return null;
  }

  @Override
  public void deleteNumber(Long id) {

  }
}
