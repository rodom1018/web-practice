package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class MemberForm {

    private Long ID;
    private String email;
    private String password;

    public Member toEntity(){
        return new Member(ID, email, password);
    }
}
