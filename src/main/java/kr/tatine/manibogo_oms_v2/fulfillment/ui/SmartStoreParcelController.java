package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentQueryParams;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.SmartStoreParcelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/smart-store-parcel")
@RequiredArgsConstructor
public class SmartStoreParcelController {

    private final FulfillmentDao fulfillmentDao;


    @GetMapping
    public List<SmartStoreParcelDto> getSmartStorePacelList(
            @ModelAttribute FulfillmentQueryParams queryParams) {

        queryParams.setItemOrderState(ItemOrderState.SHIPPED);
        queryParams.setSalesChannel(SalesChannel.SMART_STORE);

        return fulfillmentDao.findAll(queryParams).stream()
                .map(SmartStoreParcelDto::fromFulfillmentDto)
                .toList();
    }

}
