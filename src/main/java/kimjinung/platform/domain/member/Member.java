package kimjinung.platform.domain.member;

//import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.order.Order;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String password;

    private LocalDateTime joinDate;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList;
}
