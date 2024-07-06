package org.writeo.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapters")
public class Chapters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id")
    private Long chapterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", referencedColumnName = "id", nullable = false)
    private Novels novel;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;


    @Column(name = "word_count")
    private int wordCount;
    public void updateWordCount() {
        if (this.content != null && !this.content.trim().isEmpty()) {
            String[] words = this.content.trim().split("\\s+");
            this.wordCount = words.length;
        } else {
            this.wordCount = 0;
        }
    }

}
