package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
// 롤백 되어서 아예 INSERT 쿼리 조차 안나가고, 영속성 콘택스트에서 처리(em.flush가 안됨)
// 확인하고 싶으면 테스트 위에 @rollback(false) - SQL로 확인해보기
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws  Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2);
//        // (expected = IllegalStateException.class) 로 try catch 생략 가능
//        try{
//            memberService.join(member2);
//        } catch(IllegalStateException e) {
//            return;
//        }

        // then
        // 여기서는 실행이 안되는게 정상적인 상화이다!
        fail("예외가 발생해야 한다.");
    }

}