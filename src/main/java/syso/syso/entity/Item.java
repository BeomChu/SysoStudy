package syso.syso.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@Entity
public class Item {



    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @ManyToOne
    @JoinColumn(name="member_item")
    private Member member;

//    @Column(nullable = false)
//    private enum itemSellStatus;
}
