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

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;

    public void 댓글등록(Long itemId, Member member, CommentDto commentDto){
        Item item=itemRepository.findById(itemId).get();
        Comment comment=new Comment();
        comment.setItem(item);
        comment.setMember(member);
        comment.setContext(commentDto.getContext());
        commentRepository.save(comment);
    }

}
