package kr.tatine.manibogo_oms_v2.order.command.application.service;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.ProceedOrderStateCommand;
import kr.tatine.manibogo_oms_v2.order.command.application.exception.OrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProceedOrderStateService {

    private final OrderRepository orderRepository;

    @Transactional
    public void proceed(ProceedOrderStateCommand command) {

        final Order order = orderRepository
                .findById(new OrderNumber(command.orderNumber()))
                .orElseThrow(OrderNotFoundException::new);

        order.proceedState(command.targetState(), LocalDateTime.now());
    }

}
