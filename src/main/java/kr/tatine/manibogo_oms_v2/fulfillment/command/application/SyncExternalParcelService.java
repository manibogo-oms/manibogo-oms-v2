package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.TrackingInfo;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class SyncExternalParcelService {

    private final ItemOrderRepository itemOrderRepository;

    @Transactional
    public void synchronize(@Valid SyncExternalParcelCommand command) {

        final ItemOrderNumber itemOrderNumber = new ItemOrderNumber(command.itemOrderNumber());

        final ItemOrder itemOrder = itemOrderRepository.findById(itemOrderNumber)
                .orElseThrow(ItemOrderNotFoundException::new);

        itemOrder.changeTrackingInfo(new TrackingInfo(command.shippingTrackingNumber(), command.shippingCompanyName()));
        itemOrder.proceedState(ItemOrderState.SHIPPED, LocalDateTime.now());
    }

}
