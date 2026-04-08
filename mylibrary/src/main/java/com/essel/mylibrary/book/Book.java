package com.essel.mylibrary.book;

import com.essel.mylibrary.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Boolean isReserved = false;

    @ManyToOne
    @JoinColumn(name = "reserved_by")
    private User reservedBy;

}
