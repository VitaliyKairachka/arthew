package com.vitaliy.kairachka.arthew.model.dto.response;

import com.vitaliy.kairachka.arthew.model.entity.Region;
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
public class PlaceResponse {

    private Long id;
    private String name;
    private Long hotelCount;
    private Long photoCount;
    private Region region;
    private Boolean isFound = true;
}
