package syso.syso.dto;

import lombok.Data;
import syso.syso.entity.Member;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDto {

    private Member member;

    private String comment;



}
