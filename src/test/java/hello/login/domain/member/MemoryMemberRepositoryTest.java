package hello.login.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clrearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setLoginId("spring");
        member.setPassword("spring!");
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Optional<Member> findMember = repository.findByLoginId(member.getLoginId());
        assertThat(findMember.get().getLoginId()).isEqualTo(member.getLoginId());

    }

    @Test
    void findById() {
        //given
        Member member = new Member();
        member.setLoginId("spring");
        member.setPassword("spring!");
        member.setName("spring");
        Member saveMember = repository.save(member);

        //when
        Member findMember = repository.findById(saveMember.getId());

        //then
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findByLoginId() {
        //given
        Member member = new Member();
        member.setLoginId("spring");
        member.setPassword("spring!");
        member.setName("spring");
        Member saveMember = repository.save(member);

        //when
        Optional<Member> findMember = repository.findByLoginId(saveMember.getLoginId());

        //then
        assertThat(findMember.get().getLoginId()).isEqualTo(saveMember.getLoginId());
    }

    @Test
    void findByName() {
        //given
        Member member = new Member();
        member.setLoginId("spring");
        member.setPassword("spring!");
        member.setName("spring");
        Member saveMember = repository.save(member);

        //when
        Member findMember = repository.findByName(saveMember.getName()).get();

        //then
        assertThat(findMember.getName()).isEqualTo("spring");
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member();
        member1.setLoginId("spring1");
        member1.setPassword("spring1!");
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setLoginId("spring2");
        member2.setPassword("spring2!");
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}