package com.vitaliy.kairachka.arthew.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * @author Vitaliy Kayrachka
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ColumnDefault("0")
    @Column(name = "place_count")
    private Long placeCount;

    @ColumnDefault("0")
    @Column(name = "hotel_count")
    private Long hotelCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Country country;
}
