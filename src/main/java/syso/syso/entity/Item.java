package syso.syso.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item extends Timestamped{



    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @ManyToOne
    @JoinColumn(name="memberId")
    private Member member;

    @OneToMany
    @JsonIgnoreProperties({"item"})
    private List<Comment> commentList;

//    @Column(nullable = false)
//    private enum itemSellStatus;

    public static Item createItem(String itemName,int price, int stockNumber, Member member){
        Item newItem=new Item();
        newItem.setItemName(itemName);
        newItem.setPrice(price);
        newItem.setStockNumber(stockNumber);
        newItem.setMember(member);
        return newItem;
    }
}
