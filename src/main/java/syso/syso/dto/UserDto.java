package syso.syso.dto;


import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String nickName;
    private String address;
    private String email;
    private int point;
}
