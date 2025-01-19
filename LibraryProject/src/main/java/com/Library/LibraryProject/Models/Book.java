package com.OIBSIP.LibrarySystem.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column
    private String bookName;

    @Column
    private String bookAuhor;


    @Column
    private Statuses bookStatus;
    private enum Statuses{NOT_AVAILABLE, AVAILABLE};
}
