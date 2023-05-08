package kimjinung.platform.domain.member;

import kimjinung.platform.domain.base.BaseEntity;
import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.order.Order;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private final List<Order> orderHistory = new ArrayList<>();

    public Member() {

    }

    public Member(String name, String password, Address address) {
        this.name = name;
        this.password = password;
        this.address = address;
    }
}
