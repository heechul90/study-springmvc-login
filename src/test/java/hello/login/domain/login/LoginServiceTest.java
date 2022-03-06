package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemoryMemberRepository;
import hello.login.web.login.LoginForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    private final LoginService loginService;

    @Autowired
    public LoginServiceTest(LoginService loginService) {
        this.loginService = loginService;
    }

    @AfterEach
    public void afterEach() {
        repository.clrearStore();
    }

    @Test
    void loginSuccese() {
        //given
        Member member = new Member();
        member.setLoginId("spring");
        member.setPassword("spring!");
        member.setName("spring");

        Member saveMember = repository.save(member);

        //when
        Member loginMember = loginService.login(saveMember.getLoginId(), saveMember.getPassword());

        //then
        assertThat(loginMember).isEqualTo(saveMember);
    }

    @Test
    void loginFail() {
        //given
        Member member = new Member();
        member.setLoginId("spring");
        member.setPassword("spring!");
        member.setName("spring");

        //when
        Member loginMember = loginService.login("spppp", "spppp!");

        //then
        assertThat(loginMember).isNull();
    }
}