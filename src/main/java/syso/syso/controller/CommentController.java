package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.CommentDto;
import syso.syso.entity.Comment;
import syso.syso.entity.Item;
import syso.syso.repository.CommentRepository;
import syso.syso.service.CommentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping("/comment/{itemId}")
    public String createComment(@PathVariable Long itemId,String comment,@AuthenticationPrincipal PrincipalDetails principalDetails){
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment);
        commentDto.setMember(principalDetails.getMember());
        commentService.댓글등록(itemId,commentDto);
        return "성공";


    }
    @GetMapping("/comment/by/{itemId}/{page}/{size}")
    public List<CommentDto> commentByItemId(@PathVariable("itemId") Long itemId, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest pageRequest=PageRequest.of(page,size);
//        commentService.상품댓글조회(itemId);

        return commentRepository.findAll(pageRequest).getContent();

    }


}
