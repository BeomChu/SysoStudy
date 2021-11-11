package syso.syso.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import syso.syso.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o "+
            "where o.member.id = :memberId "
    )
    List<Order> findByOrders(@Param("memberId")Long memberId, Pageable pageable);

    @Query("select count(o) from Order o "+
            "where o.member.id = :memberId")
    Long countOrder(@Param("memberId")Long memberId);
}
