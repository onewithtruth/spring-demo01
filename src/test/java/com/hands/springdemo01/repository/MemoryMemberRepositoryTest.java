package com.hands.springdemo01.repository;

import com.hands.springdemo01.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("hojinandspring!!");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // static import available
    }

    @Test
    public void findByName() {
        Member member01 = new Member();
        member01.setName("spring01");
        repository.save(member01);

        Member member02 = new Member();
        member02.setName("spring02");
        repository.save(member02);

        Member result = repository.findByName("spring01").get();

        assertThat(result).isEqualTo(member01);

    }

    @Test
    public void findAll() {
        Member member01 = new Member();
        member01.setName("spring01");
        repository.save(member01);

        Member member02 = new Member();
        member02.setName("spring02");
        repository.save(member02);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
