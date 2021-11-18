package syso.syso.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private String itemNm;

    private int cnt;

    private int price;
}
