package com.example.backendjava;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/books")
public class BookController {
    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        long id = counter.incrementAndGet();
        book.setId(id);
        books.put(id, book);
        return book;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Book book = books.get(id);
        if (book == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        if (!books.containsKey(id)) return ResponseEntity.notFound().build();
        book.setId(id);
        books.put(id, book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!books.containsKey(id)) return ResponseEntity.notFound().build();
        books.remove(id);
        return ResponseEntity.noContent().build();
    }
}
