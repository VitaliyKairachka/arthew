package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import com.vitaliy.kairachka.arthew.model.entity.Number;
import com.vitaliy.kairachka.arthew.model.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author Vitaliy Kayrachka
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PhotoDto {

    private UUID id;
    private String name;
    private String file;
    private Place place;
    private Hotel hotel;
    private Number number;
}
