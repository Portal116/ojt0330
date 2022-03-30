package com.ojt.ojttest;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;
    private int price;
    private String author;
    private String publisher;
}
