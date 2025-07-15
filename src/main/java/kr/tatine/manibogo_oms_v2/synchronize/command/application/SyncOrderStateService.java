package kr.tatine.manibogo_oms_v2.synchronize.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.order.command.application.exception.OrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SyncOrderStateService {

    private final OrderRepository orderRepository;

    @Transactional
    public void synchronize(@Valid SyncOrderStateCommand command) {

        final OrderState targetState = switch (command.targetState()) {
            case "구매확정" -> OrderState.CONFIRMED;
            case "취소" -> OrderState.CANCELLED;
            case "교환" -> OrderState.REFUNDED;
            default -> throw new IllegalStateException("Unexpected value: " + command.targetState());
        };

        final Order order = orderRepository
                .findById(new OrderNumber(command.orderNumber()))
                .orElseThrow(OrderNotFoundException::new);

        order.proceedState(targetState, command.changedAt());
    }


}
