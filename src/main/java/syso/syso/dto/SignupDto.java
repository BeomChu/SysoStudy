package syso.syso.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @Size(min = 2,max = 20) //두글자 이상 20글자 이하
    private String userId;

    @NotBlank(message = "비밀번호는 꼭 넣어주세요~~")
    private String password;

    @NotBlank(message = "닉네임은 꼭 넣어주세요~~")
    private String nicName;

    @NotBlank(message = "이메일은 꼭 넣어주세요~~")
    private String email;

    @NotBlank(message = "주소는 꼭 넣어주세요~~")
    private String address;
}
