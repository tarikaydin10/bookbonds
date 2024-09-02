package dev.aydin.bookbonds.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "club_member", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "club_id"})
})
public class ClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Enumerated(EnumType.STRING)
    private ClubRole role ;

    @Override
    public int hashCode() {
        return Objects.hash(memberId); // Nur die ID verwenden, um HashCode zu berechnen
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubMember that = (ClubMember) o;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public String toString() {
        return "ClubMember{" +
                "memberId=" + memberId +
                ", user=" + (user != null ? user.getUsername() : "null") + // Nur den Benutzernamen anzeigen
                ", role=" + role +
                '}';
    }



}
