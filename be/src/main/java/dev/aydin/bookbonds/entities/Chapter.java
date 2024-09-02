package dev.aydin.bookbonds.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterId;

    private String title;
    private int chapterNumber;

    private Long clubId;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserReaction> reactions = new HashSet<>();




    private double chapterProgress = 0;


    public void updateProgress(long totalMembers) {

        long membersRead = reactions.stream()
                .filter(userReaction -> userReaction.isRead())
                .map(UserReaction::getUser)
                .distinct()
                .count();
        if (totalMembers > 0) {
            this.chapterProgress = (double) membersRead / totalMembers * 100;
        } else {
            this.chapterProgress = 0;
        }
    }

}
