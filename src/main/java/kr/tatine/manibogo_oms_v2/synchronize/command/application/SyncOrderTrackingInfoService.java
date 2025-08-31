package kr.tatine.manibogo_oms_v2.synchronize.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.order.command.application.exception.OrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.TrackingInfo;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class SyncOrderTrackingInfoService {

    private final OrderRepository orderRepository;

    @Transactional
    public void synchronize(@Valid SyncOrderTrackingInfoCommand command) {

        final OrderNumber orderNumber = new OrderNumber(command.orderNumber());

        final Order order = orderRepository
                .findById(orderNumber)
                .orElseThrow(OrderNotFoundException::new);

        order.changeTrackingInfo(new TrackingInfo(command.shippingTrackingNumber(), command.shippingCompanyName()));
        order.proceedState(OrderState.SHIPPED, LocalDateTime.now());
    }

}
