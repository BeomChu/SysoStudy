package syso.syso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import syso.syso.dto.CartDetailDto;
import syso.syso.entity.CartItem;
import syso.syso.entity.Item;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByCartId(Long cartId);

    CartItem findByCartIdAndItem(Long cartId, Item item);


    @Query("select new syso.syso.dto.CartDetailDto(c.id,i.itemName,c.cnt,i.price) " +
            "from CartItem c " +
            "join c.item i " +
            "where c.cart.id = :cartId"
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);

}
