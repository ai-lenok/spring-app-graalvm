package org.example.addressbook.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO который трансформируемся в таблицу базы данных
 */
public record AddressBook(@Id Long id, String firstName, String lastName, String phone, LocalDate birthday) {
    boolean hasId() {
        return id != null;
    }
}
