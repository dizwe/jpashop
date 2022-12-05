package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository // Spring Bean으로 Component를 Annotation 통해 등록
@RequiredArgsConstructor
public class MemberRepository {
//    // 방법1
//    @PersistenceContext // Spring의 Entity Manager를 주입받을 수 있다.
//    private EntityManager em;

    // 방법2
    // @PersistenceContext 대신에 @Autowired 써도 되어서 이렇게 RequiredArgsConstructor 사용 가능
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        // entity 객체를 table 이름으로 줘서 찾기
        // JPQL인데 from의 대상이 테이블이 아니라 Entity
        return em.createQuery("select m from Member m" , Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
