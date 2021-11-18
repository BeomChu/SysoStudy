package syso.syso.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ItemDto {

    @Size(min = 2,max = 20)
    @NotBlank(message = "상품명은 꼭 적어주세요~~")
    private String itemName;

    @NotNull(message = "가격은 꼭 넣어주세요~~")
    private Integer price;

    @NotNull(message = "상품개수는 꼭 넣어주세요~~")
    private Integer stockNumber;

}
