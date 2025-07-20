package kr.tatine.manibogo_oms_v2.order.command.application.service;

import kr.tatine.manibogo_oms_v2.order.command.application.dto.CreateOrderLogCommand;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.OrderLog;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateOrderLogService {

    private final OrderLogRepository repository;

    public void create(CreateOrderLogCommand command) {

        final OrderState previousState = getPreviousStateOrNull(command);

        final OrderState newState = OrderState.valueOf(command.newState());

        final OrderLog orderLog = new OrderLog(
                new OrderNumber(command.orderNumber()),
                previousState,
                newState,
                command.changedAt(),
                command.changedBy()
        );

        repository.save(orderLog);
    }

    private OrderState getPreviousStateOrNull(CreateOrderLogCommand command) {
        return Optional
                .ofNullable(command.previousState())
                .map(OrderState::valueOf)
                .orElse(null);
    }

}
