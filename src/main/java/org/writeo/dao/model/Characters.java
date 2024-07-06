package org.writeo.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "characters")
public class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long characterId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
//one character has multiple mentions
//    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
//    private List<Mentions> mentions;
    @Column(name = "mention_count")
    private int mention;
    // Constructors, getters, and setters
}
