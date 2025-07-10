package com.example.backendjava;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void crudBook() {
        // Create
        Book book = new Book(null, "Livro Teste", "Autor Teste", 2024);
        ResponseEntity<Book> createResp = restTemplate.postForEntity("/books", book, Book.class);
        assertThat(createResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        Book created = createResp.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        // Get
        Book get = restTemplate.getForObject("/books/" + created.getId(), Book.class);
        assertThat(get).isNotNull();
        assertThat(get.getTitle()).isEqualTo("Livro Teste");

        // Update
        created.setTitle("Novo Título");
        restTemplate.put("/books/" + created.getId(), created);
        Book updated = restTemplate.getForObject("/books/" + created.getId(), Book.class);
        assertThat(updated.getTitle()).isEqualTo("Novo Título");

        // Delete
        restTemplate.delete("/books/" + created.getId());
        ResponseEntity<Book> deleted = restTemplate.getForEntity("/books/" + created.getId(), Book.class);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
