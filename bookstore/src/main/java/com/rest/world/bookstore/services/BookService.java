package com.rest.world.bookstore.services;

import com.rest.world.bookstore.models.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;

@Service
public class BookService {
    private final ConcurrentMap<String, Book> books;

    public BookService() {
        this.books = new ConcurrentHashMap<>();
    }

    public Collection<Book> getAllBooks() {
        Collection<Book> allBooks = books.values();
        return allBooks.isEmpty() ? emptyList() : new ArrayList<>(allBooks);
    }

    public Book getBook(String oid) {
        return books.get(oid);
    }

    public void addBook(Book book) {
        if (isNull(book.getOid())){
            book.setOid(randomUUID().toString());
        }

        this.books.put(book.getOid(), book);
    }

    public Book updateBook(String oid, Book book) {
        if (books.containsKey(oid)) {
            return books.put(oid, book);
        }
        throw new RuntimeException(oid + " book not found");
    }

    public void deleteBook(String oid) {
        if (!books.containsKey(oid)) {
            throw new RuntimeException(oid + " book not found");
        }
        this.books.remove(oid);
    }
}
