package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CommentDto;
import syso.syso.dto.CommentListDto;
import syso.syso.entity.Comment;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.handler.CustomException;
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
        Item getItem = itemRepository.findById(itemId).orElseThrow(()->{
            throw new CustomException("아이템을 찾울 수 없습니다.");
        });
        Comment newComment=Comment.createComment(getItem, commentDto.getMember(),commentDto.getComment());

        commentRepository.save(newComment);
        getItem.getCommentList().add(newComment);
        return newComment.getCommentId();
    }

    public Page<CommentListDto> findCommentByItem(Long itemId){
        List<Comment> findList=commentRepository.findByItemId(itemId);
        List<CommentListDto> getList=new ArrayList<>();
        Long totalCount=commentRepository.countComment();
        Pageable pageable= PageRequest.of(0,3);
        for (Comment comment : findList) {
            CommentListDto commentDto=new CommentListDto();
            commentDto.setComment(comment.getComment());
            commentDto.setMemberNicName(comment.getMember().getNicName());
            getList.add(commentDto);
        }

        return new PageImpl<CommentListDto>(getList,pageable,totalCount);

    }

    public void updateComment(Long commentId,Member member,String comment){
        Comment getComment=commentRepository.findById(commentId).orElseThrow(()->{
            throw new CustomException("존재하지 않는 댓글입니다.");
        });
        if(getComment.getMember()==member) {
            getComment.setComment(comment);
        }else{
            throw new CustomException("작성자 본인만 수정할 수 있습니다.");
        }
    }

    public void deleteComment(Long commentId,Member member){

        Comment setComment=commentRepository.findById(commentId).orElseThrow(()->{
            throw new CustomException("존재하지 않는 댓글입니다.");
        });
        if(setComment.getMember()==member){
            commentRepository.delete(setComment);
        }else{
            throw new CustomException("작성자 본인만 삭제할 수 있습니다.");
        }



        }

}
