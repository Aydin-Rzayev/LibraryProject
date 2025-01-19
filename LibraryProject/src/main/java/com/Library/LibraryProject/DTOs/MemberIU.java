package com.OIBSIP.LibrarySystem.DTOs;

import com.OIBSIP.LibrarySystem.Roles;

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
