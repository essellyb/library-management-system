package com.essel.mylibrary.book;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponse {

    private Integer id;
    private String title;
    private String author;
    private Boolean isReserved;
    private String reservedBy;

}
