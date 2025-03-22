package com.Library.LibraryProject.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Library.LibraryProject.DTOs.MemberDTO;
import com.Library.LibraryProject.DTOs.MemberIU;
import com.Library.LibraryProject.Enums.Roles;
import com.Library.LibraryProject.Services.IMemberService;

@RestController
@RequestMapping(path = "/member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        return memberService.findAllMembers().map(ResponseEntity::ok)
                                        .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<List<MemberDTO>> getMembersByName(@PathVariable String name){
        return memberService.findMembersByName(name).map(ResponseEntity::ok)
                                                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Integer id){
        return memberService.findMemberById(id).map(ResponseEntity::ok)
                                            .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{role}")
    public ResponseEntity<List<MemberDTO>> getMembersByRole(@PathVariable Roles role){
        return memberService.findMembersByRole(role).map(ResponseEntity::ok)
                                                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{statuse}")
    public ResponseEntity<MemberDTO> getMembersByStatuse(@PathVariable String email){
        return memberService.findMembersByEmail(email).map(ResponseEntity::ok)
                                                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<MemberDTO> addMember(MemberIU memberIu){
        Optional<MemberDTO> member = memberService.existsMember(memberIu)? Optional.empty(): memberService.saveMember(memberIu);
        return member.map(b -> ResponseEntity.status(HttpStatus.CREATED).body(b)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberIU memberIu, @PathVariable Integer id){
        return memberService.updateMember(id, memberIu).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<HttpStatus> deleteMember(@RequestBody MemberIU memberIU){
        HttpStatus status = memberService.deleteMember(memberIU)? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).build();
    }
}
