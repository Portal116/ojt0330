package com.ojt.ojttest;

import com.ojt.ojttest.BookDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String helloResponse() {
        return "Hello World";
    }

    @GetMapping("/hello/dto")
    public BookDto bookResponse(@RequestParam("title") String title, @RequestParam("price") int price,
                                @RequestParam("author") String author, @RequestParam("publisher") String publisher) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setPrice(price);
        bookDto.setAuthor(author);
        bookDto.setPublisher(publisher);
        return bookDto;
    }
}
