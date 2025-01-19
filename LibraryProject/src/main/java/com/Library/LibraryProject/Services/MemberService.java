package com.OIBSIP.LibrarySystem.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.OIBSIP.LibrarySystem.Repositories.MemberRepository;
import com.OIBSIP.LibrarySystem.DTOs.*;
import com.OIBSIP.LibrarySystem.Models.Member;
import com.OIBSIP.LibrarySystem.Roles;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Optional<List<MemberDTO>> getAllMembers(){
        List<MemberDTO> members = new ArrayList<MemberDTO>();
        Iterator<Member> memberIterator = memberRepository.findAll().iterator();
        while(memberIterator.hasNext()){
            Member member = memberIterator.next();
            MemberDTO memberDto = new MemberDTO();
            BeanUtils.copyProperties(member, memberDto);
            members.add(memberDto);
        }
        return Optional.of(members);
    }

    public Optional<MemberDTO> getMemberById(Integer id){
        Optional<Member> member = memberRepository.findById(id);
        Optional<MemberDTO> memberDto = Optional.of(new MemberDTO());
        BeanUtils.copyProperties(member, memberDto);
        return memberDto;
    }

    public Optional<List<MemberDTO>> getMembersByName(String name){
        Optional<List<Member>> members = memberRepository.findByName(name);
        List<MemberDTO> memberDTOs = members.get().stream()
                                                    .map(member -> {
                                                        MemberDTO memberDto = new MemberDTO();
                                                        BeanUtils.copyProperties(member, memberDto);
                                                        return memberDto;
                                                    })
                                                    .collect(Collectors.toList());
        return Optional.of(memberDTOs);
    }

    public Optional<List<MemberDTO>> getMembersByRole(Roles role){
        Optional<List<Member>> members = memberRepository.findBYRole(role);
        List<MemberDTO> memberDTOs = members.get().stream()
                                                    .map(member -> {
                                                        MemberDTO memberDto = new MemberDTO();
                                                        BeanUtils.copyProperties(member, memberDto);
                                                        return memberDto;
                                                    })
                                                    .collect(Collectors.toList());
        return Optional.of(memberDTOs); 
    }

    public boolean existsMember(MemberIU memberIU){
        Member member = new Member();
        BeanUtils.copyProperties(memberIU, member);
        Example<Member> memberExample = Example.of(member);
        return memberRepository.exists(memberExample);
    }

    public Optional<MemberDTO> updateMember(){};

    
    
}
