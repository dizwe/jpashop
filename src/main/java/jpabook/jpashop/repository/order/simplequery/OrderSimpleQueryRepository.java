package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        // JPA 는 Entity나 value object를 반환할 수 있어서 이에 맞게 해야한다.
        // query문에서는 Entity 자체를 보낼수는 없다고 한다.
        return em.createQuery(
                        "select new jpabook.jpashop.repository.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join fetch o.member m" +
                                " join fetch o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
