package syso.syso.entity;

import lombok.Getter;
import lombok.Setter;
import syso.syso.constant.OrderStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem extends Timestamped{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    private int orderPrice;

    private int count;

    private int point;

    public static OrderItem createOrderItem(Order order,Item item,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrder(order);
        orderItem.setCount(count);

        return orderItem;
    }
}
