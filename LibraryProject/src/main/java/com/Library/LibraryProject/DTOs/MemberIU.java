package com.Library.LibraryProject.DTOs;

import com.Library.LibraryProject.Enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberIU {
    private Integer id;
    private String memberName;
    private Roles memberRole;
}
