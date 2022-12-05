package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch=FetchType.LAZY) // mappedBy는 related_name (관계한 이름으로 적는것)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // String Type으로 넣어주자!!!(중간에 추가되면 머리아포)
    private DeliveryStatus status;
}