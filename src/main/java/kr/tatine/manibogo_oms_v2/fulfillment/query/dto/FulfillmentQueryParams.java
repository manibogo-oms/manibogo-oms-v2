package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String sido;

    private String sigungu;

}
