package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA가 생성을 할 떄 Reflection이나 proxy기본 생성자가 없으므로 기보노 생성자 만들어어ㅑ 한다.
    // protected 까지는 JPA에서 만드는 것 허용해준다.
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}