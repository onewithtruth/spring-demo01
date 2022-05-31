package com.hands.springdemo01;

import com.hands.springdemo01.repository.MemberRepository;
import com.hands.springdemo01.repository.MemoryMemberRepository;
import com.hands.springdemo01.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
