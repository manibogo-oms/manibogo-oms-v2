package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.order.query.dao.OrderDao;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderQueryParams;
import kr.tatine.manibogo_oms_v2.order.query.dto.PurchaseOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 발주서(PO) 컨트롤러
 */
@RestController
@RequestMapping("/v2/purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final OrderDao orderDao;

    @GetMapping
    public List<PurchaseOrderDto> getPurchaseOrder(
            @ModelAttribute OrderQueryParams queryParams) {

        queryParams.setItemOrderState(OrderState.PURCHASED);

        return PurchaseOrderDto.FromFulfillmentDtoList(orderDao.findAll(queryParams));

    }


}
