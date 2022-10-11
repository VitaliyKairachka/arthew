package com.vitaliy.kairachka.arthew.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
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

    @ColumnDefault("0")
    @Column(name = "number_count")
    private Long numberCount;

    @ColumnDefault("0")
    @Column(name = "photo_count")
    private Long photoCount;

    @JoinColumn(name = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Place place;

    @OneToMany(mappedBy = "hotel")
    private Set<Photo> photoSet;
}
