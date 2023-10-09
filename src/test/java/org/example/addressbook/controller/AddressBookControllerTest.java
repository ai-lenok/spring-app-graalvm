package org.example.addressbook.controller;

import org.example.addressbook.model.AddressBook;
import org.example.addressbook.repository.AddressBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class AddressBookControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    AddressBookRepository repository;

    @Test
    void contextLoads() {
        assertThat(webTestClient).isNotNull();
    }

    @Test
    void testFindAll() {
        webTestClient.get()
                .uri("/api/v1/addressbooks")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("""
                        [{"id":-2,"firstName":"Petr","lastName":"Petrov","phone":"+79111111111","birthday":"1990-01-01"},
                        {"id":-1,"firstName":"Aleksey","lastName":"Alekseev","phone":"+79000000000","birthday":"1980-01-01"}]""");
    }

    @Test
    void testFindAllPagination() {
        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/api/v1/addressbooks")
                                .queryParam("page", "0")
                                .queryParam("size", "1")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("""
                        [{"id":-2,"firstName":"Petr","lastName":"Petrov","phone":"+79111111111","birthday":"1990-01-01"}]""");
    }

    @Test
    void testSave() {
        AddressBook addressBook =
                new AddressBook(null, "Ivan", "Ivanov",
                        "+79999999999", LocalDate.parse("2000-01-01"));

        final long sizeBefore = repository.findAll().count().block();

        webTestClient.post()
                .uri("/api/v1/addressbook")
                .bodyValue(addressBook)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("""
                        {"firstName": "Ivan","lastName": "Ivanov","phone": "+79999999999","birthday": "2000-01-01"}""");

        final long sizeAfter = repository.findAll().count().block();

        assertEquals(sizeBefore + 1, sizeAfter);
    }
}