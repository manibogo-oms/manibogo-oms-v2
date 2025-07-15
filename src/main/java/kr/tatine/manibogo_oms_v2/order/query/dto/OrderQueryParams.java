package kr.tatine.manibogo_oms_v2.order.query.dto;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class OrderQueryParams {

    private OrderState itemOrderState;

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

    public LocalDateTime getStartedAt() {
        if (startDate == null) return null;
        return startDate.atStartOfDay();
    }

    public LocalDateTime getEndedAt() {
        if (endDate == null) return null;
        return endDate.atTime(23, 59, 59);
    }

}
