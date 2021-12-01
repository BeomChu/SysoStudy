package syso.syso.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CommentDto;
import syso.syso.entity.Comment;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.repository.CommentRepository;
import syso.syso.repository.ItemRepository;
import syso.syso.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MemberRepository memberRepository;


    public static Item createItem() {
        Item item = new Item();
        item.setItemName("상품");
        item.setPrice(3000);
        item.setStockNumber(300);

        return item;
    }

    public static Member createMember(){
        Member member = new Member();
        member.setEmail("aaa");
        member.setPassword("aaa");
        member.setNicName("aaa");
        member.setAddress("aaa");
        member.setUserId("aaaa");

        return member;
    }


    @Test
    @DisplayName("댓글생성")
    public void createCommnet(){

        Item item=createItem();
        itemRepository.save(item);
        Member member=createMember();
        memberRepository.save(member);
        CommentDto commentDto=new CommentDto();
        commentDto.setComment("냠");
        commentDto.setMember(member);

        Long findByCommentId = commentService.댓글등록(item.getItemId(), commentDto);
        Comment comment= commentRepository.findById(findByCommentId).get();
        assertEquals(comment.getComment(),"냠");

    }


}
