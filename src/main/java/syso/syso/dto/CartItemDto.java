package syso.syso.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CartItemDto {

    private Long itemId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int cnt;
}
