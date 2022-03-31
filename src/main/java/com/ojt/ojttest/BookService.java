package com.ojt.ojttest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<?> insertBook(BookDto bookDto) {
        LocalDateTime now = LocalDateTime.now();
        try {
            bookRepository.save(bookDto);
            return new ResponseEntity<>("입력 성공", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getBook(int id) {
        try {
            Optional<BookDto> bookDto = bookRepository.findById(id);
            if(bookDto.isPresent()){
                return new ResponseEntity<>(bookDto, HttpStatus.OK);
            }
            return new ResponseEntity<>("데이터 없음", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getBookList() {
        try {
            List<BookDto> bookList = bookRepository.findAll();
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateBookById(int id, BookDto book) {
//        book.setId(id);
//        bookRepository.save(book);

        Optional<BookDto> bookDto = bookRepository.findById(id);
        if (bookDto.isPresent()) {
            book.setId(id);
            if (book.getTitle() == null) {
                book.setTitle(bookDto.get().getTitle());
            }
            if (book.getPrice() == 0) {
                book.setPrice(bookDto.get().getPrice());
            }
            if (book.getAuthor() == null) {
                book.setAuthor(bookDto.get().getAuthor());
            }
            if (book.getPublisher() == null) {
                book.setPublisher(bookDto.get().getPublisher());
            }
            bookRepository.save(book);
            return new ResponseEntity<>("수정 성공", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteBook(int id) {
        try {
            Optional<BookDto> bookDto = bookRepository.findById(id);
            if(bookDto.isPresent()){
                bookRepository.deleteById(id);
                Map<String, Object> resMap = new HashMap<>();
                resMap.put("description","삭제 성공");
                resMap.put("data",bookDto);
                return new ResponseEntity<>(resMap, HttpStatus.OK);
            }
            return new ResponseEntity<>("데이터 없음", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
