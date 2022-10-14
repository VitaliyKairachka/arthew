package com.vitaliy.kairachka.arthew.model.dto;

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
public class CountryDto {

    private Long id;
    private String name;
    private Long regionCounter;
    private Long placeCounter;
    private Long hotelCounter;
}
