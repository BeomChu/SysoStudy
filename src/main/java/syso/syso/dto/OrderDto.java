package syso.syso.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDto {

    @NotNull(message = "1개 이상 선택해주세요")
    private Integer cnt;

    private int point;
}
