package com.vitaliy.kairachka.arthew.model.dto.response;

import com.vitaliy.kairachka.arthew.model.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Vitaliy Kayrachka
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HotelResponse {

    private Long id;
    private String name;
    private Long numberCount;
    private Long photoCount;
    private Place place;
    private Boolean isFound;
}
