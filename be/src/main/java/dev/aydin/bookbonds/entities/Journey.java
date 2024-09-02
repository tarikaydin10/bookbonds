package dev.aydin.bookbonds.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "journey")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journeyId;

    @Column
    private String title;
    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Chapter> chapters = new HashSet<>();



    // Neues Attribut für den Fortschritt
    private double progress;


    private int numberOfChapters;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public void updateProgress() {
        double chapterProgressSum = 0.0;
        for(Chapter c : chapters){
            chapterProgressSum += c.getChapterProgress();
        }
        progress = chapterProgressSum/chapters.size();
    }

        @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey that = (Journey) o;
        return Objects.equals(journeyId, that.journeyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(journeyId);
    }
}
