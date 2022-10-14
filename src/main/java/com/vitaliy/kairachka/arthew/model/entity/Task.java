package com.vitaliy.kairachka.arthew.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notification")
    private String notification;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
}
