package com.essel.mylibrary.book;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotBlank
    String title;

    @NotBlank
    String author;
}
