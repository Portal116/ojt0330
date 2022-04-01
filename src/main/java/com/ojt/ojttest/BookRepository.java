package com.ojt.ojttest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookDto, Integer> {
//    BookDto findById(int id);
//    List<BookDto> findByTitleLike(String title);
//    List<BookDto> findByPriceAtBetween(int price1, int price2);
//    List<BookDto> findByAuthorLike(String author);
//    List<BookDto> findByPublisherLike(String publisher);
//    List<BookDto> findByCreatedDateAtBetween(LocalDateTime fromDate, LocalDateTime toDate);
//    List<BookDto> findByModifiedDateAtBetween(LocalDateTime fromDate, LocalDateTime toDate);
}