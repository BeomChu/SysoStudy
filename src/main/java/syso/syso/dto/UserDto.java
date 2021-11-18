package syso.syso.dto;


import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String nicName;
    private String address;
    private String email;
    private int point;
}
