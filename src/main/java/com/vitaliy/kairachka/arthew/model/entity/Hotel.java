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
@Table(name = "hotels")
public class Hotel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "number_count")
    private Long numberCount;

    @Column(name = "photo_count")
    private Long photoCount;

    @ManyToOne(targetEntity = Place.class)
    @JoinColumn(name = "place_id")
    private Place place;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Number> numbers;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private Set<Photo> photoSet;
}
