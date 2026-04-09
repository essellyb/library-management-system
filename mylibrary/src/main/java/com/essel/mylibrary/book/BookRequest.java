package com.essel.mylibrary.book;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotBlank(message = "Set a title for the book")
    String title;

    @NotBlank(message = "Set an author for the book")
    String author;
}
