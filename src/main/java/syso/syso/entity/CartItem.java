package syso.syso.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Item item;

    private int cnt;
}
