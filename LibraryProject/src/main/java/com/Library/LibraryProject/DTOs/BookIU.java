package com.Library.LibraryProject.DTOs;


import com.Library.LibraryProject.Enums.Statuses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookIU {
    private Integer id;
    private String bookName;
    private Statuses bookStatuse;
}
