package syso.syso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syso.syso.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
