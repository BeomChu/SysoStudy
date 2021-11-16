package syso.syso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syso.syso.entity.Cart;
import syso.syso.entity.Member;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByMember(Member member);
}
