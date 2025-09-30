package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.SalesChannel;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderQueryUseCase;
import kr.tatine.manibogo_oms_v2.order.query.dto.in.OrderQueryParams;
import kr.tatine.manibogo_oms_v2.order.query.dto.SmartStoreParcelDto;
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

    private final OrderQueryUseCase orderQueryUseCase;


    @GetMapping
    public List<SmartStoreParcelDto> getSmartStorePacelList(
            @ModelAttribute OrderQueryParams queryParams) {

        queryParams.setItemOrderState(OrderState.SHIPPED);
        queryParams.setSalesChannel(SalesChannel.SMART_STORE);

        return orderQueryUseCase.findAll(queryParams).stream()
                .map(SmartStoreParcelDto::fromFulfillmentDto)
                .toList();
    }

}
