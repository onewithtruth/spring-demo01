package com.hands.springdemo01.service;

import com.hands.springdemo01.domain.Member;
import com.hands.springdemo01.repository.MemberRepository;
import com.hands.springdemo01.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest //스프링 컨테이너와 테스트 함께 실행
@Transactional // Test 인 경우 완료후 롤백
class MemberServiceIntegrationTest {

    @Autowired // 필드 인젝션
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Commit
    void join() {
        //given
        Member member = new Member();
        member.setName("spring with hojin!!!");


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
        member01.setName("spring001");

        Member member02 = new Member();
        member02.setName("spring001");

        //when
        memberService.join(member01);
//        try {
//            memberService.join(member02);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("the name is already existing");
//        }

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member02));
        assertThat(e.getMessage()).isEqualTo("the name is already existing");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}