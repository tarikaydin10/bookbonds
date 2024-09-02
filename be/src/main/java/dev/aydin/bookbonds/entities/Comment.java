package dev.aydin.bookbonds.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> replies;

    @ManyToOne
    private Chapter chapter;

    @Override
    public int hashCode() {
        // Avoid including the `replies` field in hashCode or equals
        return Objects.hash(commentId);  // Or use other fields that uniquely identify the entity
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment comment = (Comment) obj;
        return Objects.equals(commentId, comment.commentId);
    }

}
