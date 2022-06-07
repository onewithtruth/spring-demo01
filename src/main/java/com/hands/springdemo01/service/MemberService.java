package com.hands.springdemo01.service;

import com.hands.springdemo01.domain.Member;
import com.hands.springdemo01.repository.MemberRepository;
import com.hands.springdemo01.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join (Member member) {
        //동일이름 회원 허용X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(e -> {
//            throw new IllegalStateException("the name is already existing");
//        });
        validateDuplicatedMember(member); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(e -> {throw new IllegalStateException("the name is already existing");
                });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
