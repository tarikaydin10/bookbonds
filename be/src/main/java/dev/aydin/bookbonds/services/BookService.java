package dev.aydin.bookbonds.services;

import dev.aydin.bookbonds.entities.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    Book addBook(Book bookDetails);

    Book findBookByIsbn13(String isbn13);


    List<Book> searchBooks(String query);
}
