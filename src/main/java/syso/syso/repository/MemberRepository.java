package syso.syso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syso.syso.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUserId(String userId);
}
