package com.Library.LibraryProject.Services;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Library.LibraryProject.Repositories.MemberRepository;

import jakarta.transaction.Transactional;

import com.Library.LibraryProject.DTOs.*;
import com.Library.LibraryProject.Enums.Roles;
import com.Library.LibraryProject.Models.Member;

@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Optional<List<MemberDTO>> findAllMembers(){
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

    @Override
    public Optional<MemberDTO> findMemberById(Integer id){
        Optional<Member> member = memberRepository.findById(id);
        Optional<MemberDTO> memberDto = Optional.of(new MemberDTO());
        BeanUtils.copyProperties(member, memberDto);
        return memberDto;
    }

    @Override
    public Optional<List<MemberDTO>> findMembersByName(String name){
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

    @Override
    public Optional<List<MemberDTO>> findMembersByRole(Roles role){
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

    @Override
    public boolean existsMember(MemberIU memberIU){
        Member member = new Member();
        BeanUtils.copyProperties(memberIU, member);
        Example<Member> memberExample = Example.of(member);
        return memberRepository.exists(memberExample);
    }

    @Override
    @Transactional
    public Optional<MemberDTO> updateMember(MemberIU memberIU){
        Optional<MemberDTO> optionalMemberDto = Optional.ofNullable(new MemberDTO());
        if(existsMember(memberIU)){
            Member member = new Member();
            BeanUtils.copyProperties(memberIU, member);
            BeanUtils.copyProperties(memberRepository.updateMember(member).get(), optionalMemberDto);
        }
        return optionalMemberDto;
    }

    @Override
    @Transactional
    public Optional<MemberDTO> saveMember(MemberIU memberIU){
        Optional<MemberDTO> optionalMemberDto = Optional.ofNullable(new MemberDTO());
        if(!existsMember(memberIU)){
            Member member = new Member();
            BeanUtils.copyProperties(memberIU, member);
            memberRepository.save(member);
            MemberDTO memberDTO = new MemberDTO();
            BeanUtils.copyProperties(member, memberDTO);
            optionalMemberDto = Optional.of(memberDTO); 
        }
        return optionalMemberDto;
    }

    @Override
    @Transactional
    public boolean deleteMember(MemberIU memberIU){
        if(existsMember(memberIU)){
            Member member = new Member();
            BeanUtils.copyProperties(memberIU, member);
            memberRepository.delete(member);
            return true;
        }
        return false;
    }

    
    
}
