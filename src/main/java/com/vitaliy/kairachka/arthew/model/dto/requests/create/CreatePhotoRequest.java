package com.vitaliy.kairachka.arthew.model.dto.requests.create;

import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import com.vitaliy.kairachka.arthew.model.entity.Number;
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
public class CreatePhotoRequest {

    private String name;
    private String file;
    private Place place;
    private Hotel hotel;
    private Number number;
}
