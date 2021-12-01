package syso.syso.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import syso.syso.dto.CommentDto;
import syso.syso.entity.Comment;
import syso.syso.entity.Item;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//    Page<Comment>findAllByItemId(Optional<Item>itemId, Pageable pageable);
    @Modifying
    @Query(value = "SELECT * FROM comment WHERE itemId =:itemId",nativeQuery = true)
    List<Comment> findByItemId(@Param("itemId") Long itemId);

    @Query("select count(o) from Comment o ")
    Long countComment();





}
