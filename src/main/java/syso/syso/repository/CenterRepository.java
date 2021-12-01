package syso.syso.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import syso.syso.dto.CenterListDto;
import syso.syso.dto.CenterOneDto;
import syso.syso.entity.Center;
import syso.syso.entity.Order;

import java.util.List;

public interface CenterRepository extends JpaRepository<Center, Long> {

    @Query("select new syso.syso.dto.CenterListDto(c.title,m.nicName) "+
            "from Center c " +
            "join c.member m"
    )
    List<CenterListDto> findByCenters(Pageable pageable);

    @Query("select count(o) from Order o ")
    Long countOrder();

    @Query("select new syso.syso.dto.CenterOneDto(c.title,c.content,c.member.nicName) "+
            "from Center c " +
            "where c.id = :centerId"
    )
    CenterOneDto findByCenterOneDto(Long centerId);

}
