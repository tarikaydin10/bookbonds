package dev.aydin.bookbonds.controller;

import dev.aydin.bookbonds.entities.Book;
import dev.aydin.bookbonds.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book bookDetails) {
        Book createdBook = bookService.addBook(bookDetails);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping("/{isbn13}")
    public ResponseEntity<Book> getBookByIsbn13(@PathVariable String isbn13) {
        Book bookOpt = bookService.findBookByIsbn13(isbn13);
        if(bookOpt == null){
            System.out.println("Buch nicht gefunden!!!");
        }
        return bookOpt != null ?  ResponseEntity.ok(bookOpt) : ResponseEntity.notFound().build() ;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks().stream()
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(value = "query") String query) {
        List<Book> searchResults = bookService.searchBooks(query);
        List<Book> books = searchResults.stream()
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
}
