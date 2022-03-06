package hello.login.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByName(String name);

    List<Member> findAll();

}
