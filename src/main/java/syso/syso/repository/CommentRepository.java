package syso.syso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syso.syso.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
