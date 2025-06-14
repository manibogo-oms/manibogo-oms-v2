package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentQueryParams;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.PurchaseOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<PurchaseOrderDto> getPurchaseOrder(@RequestParam String productNumber) {

        FulfillmentQueryParams queryParams = new FulfillmentQueryParams();

        queryParams.setItemOrderState(ItemOrderState.PURCHASED);
        queryParams.setProductNumber(productNumber);

        return PurchaseOrderDto.FromFulfillmentDtoList(fulfillmentDao.findAll(queryParams));

    }


}
