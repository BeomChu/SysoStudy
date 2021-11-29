package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CommentDto;
import syso.syso.entity.Comment;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.repository.CommentRepository;
import syso.syso.repository.ItemRepository;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;

    public Long 댓글등록(Long itemId, CommentDto commentDto) {
        Item byId = itemRepository.getById(itemId);
        Comment comment=new Comment();
        comment.setItem(byId);
        comment.setMember(commentDto.getMember());
        comment.setComment(commentDto.getComment());
        commentRepository.save(comment);

        return comment.getCommentId();
    }
    //상품
    public List<CommentDto> 상품댓글조회(Long itemId){
        List<Comment> findList=commentRepository.findByItemId(itemId);
        List<CommentDto> getList=new ArrayList<>();
        for (Comment comment : findList) {
            CommentDto commentDto=new CommentDto();
            commentDto.setComment(comment.getComment());
            commentDto.setMember(comment.getMember());
            getList.add(commentDto);
        }

        return getList;

    }






}
