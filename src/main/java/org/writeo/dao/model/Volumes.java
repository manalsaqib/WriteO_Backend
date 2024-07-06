package org.writeo.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "volumes")
public class Volumes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volume_ID")
    private Long volumeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_ID", referencedColumnName = "Id")
    private Novels novel;

    @Column(name = "volume_number")
    private Integer volumeNumber;

    @Column(name = "starting_chapter")
    private Integer startingChapter;

    @Column(name = "ending_chapter")
    private Integer endingChapter;

    @Column(name = "volume_name")
    private String volumeName;


}
