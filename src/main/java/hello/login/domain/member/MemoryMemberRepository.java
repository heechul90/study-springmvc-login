package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
@Primary
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        /*List<Member> all = findAll();
        for (Member m : all) {
            if (m.getLoginId().equals(loginId)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();*/
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return findAll().stream()
                .filter(m -> m.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clrearStore() {
        store.clear();
    }
}
