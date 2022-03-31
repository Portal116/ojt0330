package com.ojt.ojttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @PutMapping(value = "/posts")
    public ResponseEntity<?> insertBook(@RequestBody BookDto bookDto){
        return bookService.insertBook(bookDto);
    }

    @GetMapping("/posts/{pk}")
    public ResponseEntity<?> getBook(@PathVariable("pk") int id){
        return bookService.getBook(id);
    }

    @PutMapping("/posts/{pk}")
    public ResponseEntity<?> updateBook(@PathVariable("pk") int id, @RequestBody BookDto bookDto){
        return bookService.updateBookById(id, bookDto);
    }

    @DeleteMapping("/posts/{pk}")
    public ResponseEntity<?> deleteBook(@PathVariable("pk") int id){
        return bookService.deleteBook(id);
    }
}
