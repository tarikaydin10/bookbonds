package dev.aydin.bookbonds.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;


@Data
@Entity
@Table(name = "book", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title"),
        @UniqueConstraint(columnNames = "isbn10"),
        @UniqueConstraint(columnNames = "isbn13")
})
public class Book {

    @Id
    private String isbn13;

    @Column(nullable = false, unique = true)
    private String title;
    @Column
    private String subtitle;
    @Column
    private String author;
    @Column
    private Integer numberOfPages;
    @Column
    private Integer numberOfChapters;
    @Column
    private String isbn10;
    @Column
    private String publisher;
    @Column(nullable = false)
    private LocalDate publishDate;
    @Column(length = 2000)
    private String description;
    @Column
    private String coverImage;
    @Column
    private Double averageRating;
    @Column
    private Integer readCount;
    @Column
    private Integer toReadCount;
    @Column
    private Integer totalCount;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Journey> journeys = new HashSet<>();




    @Override
    public String toString() {
        return "User{" +
                "id=" + isbn13 +
                ", name='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn13, book.getIsbn13());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn13);
    }

}



/*
User:

user_id (PK)
username
password (verschlüsselt)
email
profile_picture


Book:
-
book_id (PK)
title unique
subtitle
authors array of arrays?
number_of_pages
identifiers
    isbn 10
    isbn 13
    openlibrary?
publishers
publish_date
description
cover_image
ISBN unique
durchsch rating
anzahl read
anzahl to read
anzahl total



User_Book:
-
user_id (FK)
book_id (FK)
status (e.g., "read", "to_read")
rating
review



Bookshelf:

shelf_id (PK)
name
description
user_id (FK)
books_total
books_toread
books_read
durchschn rating


Bookshelf_Book:

shelf_id (FK)
book_id (FK)
*/