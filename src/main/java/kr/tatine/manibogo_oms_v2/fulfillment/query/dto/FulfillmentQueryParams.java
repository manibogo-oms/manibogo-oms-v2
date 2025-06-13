package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FulfillmentQueryParams {

    private ItemOrderState itemOrderState;

    private SalesChannel salesChannel;

    private String productNumber;

    private DetailSearchParam detailSearchParam;

    private String detailSearch;

    private DateSearchParam dateSearchParam;

    private LocalDate startDate;

    private LocalDate endDate;

}
