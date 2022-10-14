package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.NumberResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface NumberService {

    List<NumberResponse> getAllNumbers(Pageable pageable);

    NumberResponse getNumberById(Long id);

    NumberResponse createNumber(CreateNumberRequest request);

    NumberResponse updateNumber(Long id, NumberDto numberDto);

    void deleteNumber(Long id);
}
