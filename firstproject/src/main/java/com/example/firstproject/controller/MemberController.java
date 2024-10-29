package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String CreateMember(){
        return "members/new";
    }

    @PostMapping("/join")
    public String NewMember(MemberForm form){
        Member now_member = form.toEntity();
        log.info(now_member.toString());
        Member member = memberRepository.save(now_member);
        log.info(member.toString());
        return "redirect:/members/" + member.getID();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);

        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);


        return "members/index";
    }


    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", member);

        return  "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm memberForm){
        Member member = memberForm.toEntity();
        memberRepository.save(member);
        return "redirect:/members/"+member.getID();

    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Member member = memberRepository.findById(id).orElse(null);

        if(member != null){
            memberRepository.delete(member);
            rttr.addFlashAttribute("msg", "삭제했습니다~ ");
        }

        return "redirect:/members";
    }



}
