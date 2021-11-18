package syso.syso.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.entity.Comment;
import syso.syso.entity.Item;
import syso.syso.repository.CommentRepository;
import syso.syso.repository.ItemRepository;
import syso.syso.repository.MemberRepository;

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

    public void createCommnet(){
        Comment c=new Comment();

    }

    @Test
    @DisplayName("댓글글등록테트")
    public void 댓글등록테스트(){


    }
}
