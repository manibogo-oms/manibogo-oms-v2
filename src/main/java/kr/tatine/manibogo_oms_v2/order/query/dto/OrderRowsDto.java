package kr.tatine.manibogo_oms_v2.order.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderRowsDto {

    private List<OrderRowDto> rows;

}
