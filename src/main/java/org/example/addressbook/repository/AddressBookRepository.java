package org.example.addressbook.repository;

import org.example.addressbook.model.AddressBook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Repository для обращения к базе данных
 * <p>
 * Использует R2DBC для асинхронных обращений к БД
 */
public interface AddressBookRepository extends ReactiveCrudRepository<AddressBook, Long> {
    Flux<AddressBook> findAllBy(Pageable pageable);
}
