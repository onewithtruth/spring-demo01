package com.hands.springdemo01.service;

import com.hands.springdemo01.domain.Member;
import com.hands.springdemo01.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("helloSpring");


        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicatedMemberException() {
        //given
        Member member01 = new Member();
        member01.setName("helloSpring");

        Member member02 = new Member();
        member02.setName("helloSpring");

        //when
        memberService.join(member01);
//        try {
//            memberService.join(member02);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("the name is already existing");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member02));
        assertThat(e.getMessage()).isEqualTo("the name is already existing");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}