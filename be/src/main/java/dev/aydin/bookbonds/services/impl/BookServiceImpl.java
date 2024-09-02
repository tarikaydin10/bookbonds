package dev.aydin.bookbonds.services.impl;


import dev.aydin.bookbonds.entities.Book;
import dev.aydin.bookbonds.repositories.BookRepository;
import dev.aydin.bookbonds.repositories.UserRepository;
import dev.aydin.bookbonds.services.BookService;
import dev.aydin.bookbonds.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;
    private final JWTService jwtService;
    @Autowired
    private final UserRepository userRepository;

    private final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private final String OPEN_LIBRARY_API = "https://openlibrary.org/api/books?bibkeys=ISBN:";
    private final String OPEN_LIBRARY_API_SUF = "&jscmd=details&format=json";


    public Book addBook(Book bookDetails) {
        Optional<Book> existingBookOpt = bookRepository.findByIsbn13(bookDetails.getIsbn13());
        return existingBookOpt.orElseGet(() -> bookRepository.save(bookDetails));
    }
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findBookByIsbn13(String isbn13) {
        Book book = bookRepository.findByIsbn13(isbn13).orElse(null);
        if (book != null) {
            return book;
        }
        // Wenn das Buch nicht in der lokalen Datenbank existiert, rufe die Daten von einer externen API ab
        RestTemplate restTemplate = new RestTemplate();
        Book externalBook = fetchBookFromExternalAPI(isbn13, restTemplate);

        if (externalBook != null) {
            // Speichere das Buch in der Datenbank
            return bookRepository.save(externalBook);
        }
        return null;
    }

    @Override
    public List<Book> searchBooks(String query) {

        return bookRepository.searchBooksByMultipleFields(query);


    }

    private Book fetchBookFromExternalAPI(String isbn13, RestTemplate restTemplate) {
        // Beispiel für die Google Books API
        String googleBooksUrl = GOOGLE_BOOKS_API + isbn13;
        String openLibraryUrl = OPEN_LIBRARY_API + isbn13 + "&format=json&jscmd=data";

        try {
            // Hier kannst du entscheiden, welche API du verwenden möchtest
            // Open Library API Beispiel:
            /*
            https://openlibrary.org/api/books?bibkeys=ISBN:9783608932225&jscmd=details&format=json
            OpenLibraryResponse openLibraryResponse = restTemplate.getForObject(openLibraryUrl, OpenLibraryResponse.class);


            if (openLibraryResponse != null) {
                // Mapping der Open Library API-Daten auf dein Book-Objekt
                Book book = mapOpenLibraryResponseToBook(openLibraryResponse, isbn13);
                return book;
            }
            */


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
