package syso.syso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syso.syso.entity.Item;



public interface ItemRepository extends JpaRepository<Item,Long> {

}
