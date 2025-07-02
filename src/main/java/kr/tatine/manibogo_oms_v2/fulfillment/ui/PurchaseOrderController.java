package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentQueryParams;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.PurchaseOrderDto;
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

    private final FulfillmentDao fulfillmentDao;

    @GetMapping
    public List<PurchaseOrderDto> getPurchaseOrder(
            @ModelAttribute FulfillmentQueryParams queryParams) {

        queryParams.setItemOrderState(OrderState.PURCHASED);

        return PurchaseOrderDto.FromFulfillmentDtoList(fulfillmentDao.findAll(queryParams));

    }


}
