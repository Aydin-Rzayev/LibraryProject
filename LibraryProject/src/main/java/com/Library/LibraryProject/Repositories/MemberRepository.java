package com.OIBSIP.LibrarySystem.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OIBSIP.LibrarySystem.Roles;
import com.OIBSIP.LibrarySystem.Models.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Optional<List<Member>> findByName(String memberName);
    public Optional<List<Member>> findBYRole(Roles role);
}
