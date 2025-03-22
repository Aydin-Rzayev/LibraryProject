package com.Library.LibraryProject.Services;

import java.util.List;
import java.util.Optional;

import com.Library.LibraryProject.DTOs.MemberDTO;
import com.Library.LibraryProject.DTOs.MemberIU;
import com.Library.LibraryProject.Enums.Roles;


public interface IMemberService {

    Optional<List<MemberDTO>> findAllMembers();

    Optional<MemberDTO> findMemberById(Integer id);

    Optional<List<MemberDTO>> findMembersByName(String name);

    Optional<List<MemberDTO>> findMembersByRole(Roles role);

    Optional<MemberDTO> findMembersByEmail(String email);

    boolean existsMember(MemberIU memberIU);

    Optional<MemberDTO> updateMember(Integer id, MemberIU memberIU);

    Optional<MemberDTO> saveMember(MemberIU memberIU);

    boolean deleteMember(MemberIU memberIU);

}