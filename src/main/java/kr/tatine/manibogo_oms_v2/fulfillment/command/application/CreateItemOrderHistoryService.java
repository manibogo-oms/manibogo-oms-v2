package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.ItemOrderHistory;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository.ItemOrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateItemOrderHistoryService {

    private final ItemOrderHistoryRepository repository;

    @Transactional
    public void create(ItemOrderHistoryCommand command) {

        final ItemOrderState previousState = getPreviousStateOrNull(command);

        final ItemOrderState newState = ItemOrderState.valueOf(command.newState());

        final ItemOrderNumber itemOrderNumber =
                new ItemOrderNumber(command.itemOrderNumber());

        final ItemOrderHistory history = new ItemOrderHistory(
                itemOrderNumber,
                previousState,
                newState,
                command.itemOrderPlacedAt()
        );

        repository.save(history);
    }

    private ItemOrderState getPreviousStateOrNull(ItemOrderHistoryCommand command) {
        return Optional
                .ofNullable(command.previousState())
                .map(ItemOrderState::valueOf)
                .orElse(null);
    }

}
