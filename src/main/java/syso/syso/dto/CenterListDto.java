package syso.syso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import syso.syso.entity.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterListDto {

    private String title;

    private String memberName;
}
