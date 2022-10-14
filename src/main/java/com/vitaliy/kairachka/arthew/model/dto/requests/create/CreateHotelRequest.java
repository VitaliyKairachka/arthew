package com.vitaliy.kairachka.arthew.model.dto.requests.create;

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
public class CreateHotelRequest {

    private String name;
    private Place place;
}
