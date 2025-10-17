package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.order.query.dto.PurchaseOrderDto;
import kr.tatine.manibogo_oms_v2.order.query.dto.in.OrderQueryParams;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 발주서(PO) 컨트롤러
 */
@RestController
@RequestMapping("/v2/purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final OrderQueryUseCase orderQueryUseCase;

    @GetMapping
    public List<PurchaseOrderDto> getPurchaseOrder(
            @ModelAttribute OrderQueryParams queryParams) {

        queryParams.setItemOrderState(OrderState.PURCHASED);

        return PurchaseOrderDto.FromFulfillmentDtoList(orderQueryUseCase.findAll(queryParams));

    }


}
