package syso.syso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import syso.syso.entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterOneDto {

    private String title;
    private String content;
    private String memberName;
}
