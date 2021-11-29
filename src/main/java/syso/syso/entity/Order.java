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
public class Order extends Timestamped{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
