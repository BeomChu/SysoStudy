package syso.syso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syso.syso.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
