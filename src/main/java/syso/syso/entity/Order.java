package syso.syso.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import syso.syso.constant.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
