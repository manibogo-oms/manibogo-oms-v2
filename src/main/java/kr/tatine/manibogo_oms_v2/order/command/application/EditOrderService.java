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
    public void editSummary(EditOrderSummaryCommand command) {

        final Order order = orderRepository
                .findById(new OrderNumber(command.orderNumber()))
                .orElseThrow(OrderNotFoundException::new);

        order.changeState(command.state(), LocalDateTime.now());
        order.changeDispatchDeadline(command.dispatchDeadline());
        order.changePreferredShippingDate(command.preferredShippingDate());
        order.changeMemo(createMemo(command));
    }

    private Memo createMemo(EditOrderSummaryCommand command) {
        return new Memo(command.purchaseMemo(), command.shippingMemo(), command.adminMemo());
    }

}
