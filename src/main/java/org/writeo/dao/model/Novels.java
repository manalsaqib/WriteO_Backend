package org.writeo.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "novels")
public class Novels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "total_volumes")
    private int totalVolumes;

    @Lob // For large binary data
    @Column(name = "title_pic", columnDefinition = "TEXT")
    private String titlePic; // Byte array to store picture data

    @Column(name = "genre")
    private String genre;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "completion_status")
    private String completionStatus;

   // @Column(name = "author_id", nullable = false)
    private Long authorId;
    @PrePersist
    protected void onCreate() {
        this.startDate = LocalDate.now();
    }
    public void incrementTotalVolumes() {
        this.totalVolumes++;
    }
}
