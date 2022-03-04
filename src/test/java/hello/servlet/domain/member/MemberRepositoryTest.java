package hello.servlet.domain.member;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//테스트의 순서는 보장하지 않음.
class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given : 주어질 때
        Member member = new Member("hello", 20);
        //when : 실행할 때
        Member savedMember = memberRepository.save(member);
        //then : 결과는 이래야 돼
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);    //org.assertj
        //찾은 멤버는 저장한 멤버와 같아야한다.
    }

   /* @Test
    void getInstance() {
    }


    @Test
    void findById() {
    }
*/
    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);         //개수
        assertThat(result).contains(member1, member2);  //멤버1, 멤버2라는 객체가 있는가?
        //alt + Enter ...> static import

    }

   /* @Test
    void clearStore() {
    }*/
}