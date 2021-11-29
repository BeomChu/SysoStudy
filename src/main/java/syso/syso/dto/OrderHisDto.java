package syso.syso.dto;

import lombok.Data;
import syso.syso.constant.OrderStatus;

import java.util.List;

@Data
public class OrderHisDto {

    private OrderStatus orderStatus;

    private List<OrderItemDto> orderItemDtos;
}
