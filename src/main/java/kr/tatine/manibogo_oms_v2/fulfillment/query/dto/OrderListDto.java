package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderListDto {

    private List<OrderDto> orders;

}
