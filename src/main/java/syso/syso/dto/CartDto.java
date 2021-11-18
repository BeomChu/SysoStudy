package syso.syso.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private List<CartDetailDto> cartDetailDtoList;

    private int totalPrice;
}
