package syso.syso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto {

    private Long cartItemId;

    private String itemNm;

    private int cnt;

    private int price;

}
