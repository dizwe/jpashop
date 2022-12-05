package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
// 기본적으로 트랜잭션 안에서 데이터 변경을 해야한다(public method에 기본적으로 트랜잭션이 걸린다)
@Transactional(readOnly=true) // JPA가 영속성에서 더티체킹을 안해서 빨라지게 할 수 있음
@RequiredArgsConstructor
public class MemberService {
//    // 방법1: 그대로 박기
//    @Autowired // injection -> 이렇게하면 나중에 주입하기가 힘들다.
//    private MemberRepository memberRepository;
//
//    // 방법2: setter
//    // spring이 바로 주입하는 것이 아니라, 중간에 다른 목 같은 것들을 넣을 수 있으
//    // 단점: public이라 다른데서 런타임에 넣을수도 있긴 함(실제로 개발하는 중간에 조립이 다 끝나서 굳이 바꿀일이 없다)
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//    // 방법3: 생성자
//    // 생성자로서 처음 만들때 주입하는 방법
//    private MemberRepository memberRepository;
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    // 방법4: AutoWired annotion 없이 자동으로 주입
//    private final MemberRepository memberRepository;
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 방법5: lombok 사용(RequiredArgumentConstructor)
    // 알아서 final 붙은 것들 알아서 setting
    private final MemberRepository memberRepository;


    // 회원 가입
    @Transactional // readonly False
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        // 그래도 멀티 쓰레드면 충돌날수 있으므로, 디비 유니크 조건도 같이 들어줘야 한다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
