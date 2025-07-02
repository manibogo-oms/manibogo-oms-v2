package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.Memo;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.Shipping;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EditOrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void edit(EditOrderCommand command) {

        final Order order = orderRepository
                .findById(new OrderNumber(command.orderNumber()))
                .orElseThrow(OrderNotFoundException::new);

        order.changeState(command.state(), LocalDateTime.now());
        order.changeShipping(createShipping(command));
        order.changeMemo(createMemo(command));
    }

    private Memo createMemo(EditOrderCommand command) {
        return new Memo(command.purchaseMemo(), command.shippingMemo(), command.adminMemo());
    }

    private Shipping createShipping(EditOrderCommand command) {
        return new Shipping(command.shippingMethod(), command.shppingChargeType(), command.dispatchDeadline(), command.preferredShippingDate());
    }

}
