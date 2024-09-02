package dev.aydin.bookbonds.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reactionId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isRead;
    private boolean isFavorited;
}

