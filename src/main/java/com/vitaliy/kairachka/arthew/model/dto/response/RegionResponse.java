package com.vitaliy.kairachka.arthew.model.dto.response;

import com.vitaliy.kairachka.arthew.model.entity.Country;
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
public class RegionResponse {

    private Long id;
    private String name;
    private Long placeCount;
    private Long hotelCount;
    private Country country;
    private Boolean isFound;
}
