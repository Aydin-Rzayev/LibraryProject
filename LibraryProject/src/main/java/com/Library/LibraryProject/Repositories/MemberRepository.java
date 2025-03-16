package com.Library.LibraryProject.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.LibraryProject.Enums.Roles;
import com.Library.LibraryProject.Models.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Optional<List<Member>> findByName(String memberName);
    public Optional<List<Member>> findBYRole(Roles role);
    public Optional<Member> updateMember(Member member);
}
