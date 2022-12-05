package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장타입을 넣었따고 어노테이션!
    private Address address;

    @OneToMany(mappedBy = "member") // 나는 연관관계의 주인이 아니에요!
    private List<Order> orders = new ArrayList<>();
}
