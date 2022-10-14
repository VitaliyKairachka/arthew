package com.vitaliy.kairachka.arthew.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author Vitaliy Kayrachka
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "places")
public class Place {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "hotel_count")
    private Long hotelCount;

    @Column(name = "photo_count")
    private Long photoCount;

    @ManyToOne(targetEntity = Region.class)
    @JoinColumn(name = "region_id")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Hotel> hotels;

    @JsonIgnore
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private Set<Photo> photoSet;
}
