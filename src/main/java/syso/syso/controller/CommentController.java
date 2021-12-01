package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.CMRespDto;
import syso.syso.dto.CommentDto;
import syso.syso.dto.CommentListDto;
import syso.syso.entity.Comment;
import syso.syso.entity.Item;
import syso.syso.repository.CommentRepository;
import syso.syso.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
//
    @PostMapping("/comment/{itemId}")
    public ResponseEntity<?> createComment(@PathVariable Long itemId,String comment,@AuthenticationPrincipal PrincipalDetails principalDetails){
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment);
        commentDto.setMember(principalDetails.getMember());
        commentService.댓글등록(itemId,commentDto);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 등록 성공",null),HttpStatus.OK);


    }

    @GetMapping("/comment/{itemId}")
    public Page<CommentListDto> findCommentByItem(@PathVariable Long itemId){
        return commentService.findCommentByItem(itemId);

    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @AuthenticationPrincipal PrincipalDetails principalDetails,String comment){
        commentService.updateComment(commentId,principalDetails.getMember(),comment);

        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 수정 성공",null), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        commentService.deleteComment(commentId,principalDetails.getMember());

        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 삭제 성공",null),HttpStatus.OK);

    }



}
