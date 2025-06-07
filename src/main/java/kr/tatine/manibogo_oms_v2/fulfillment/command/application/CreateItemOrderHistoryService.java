package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import kr.tatine.manibogo_oms_v2.common.converter.StringToDescribableEnumConverter;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.ItemOrderHistory;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository.ItemOrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateItemOrderHistoryService {

    private final ItemOrderHistoryRepository repository;

    @Transactional
    public void create(
            String itemOrderNumber,
            String itemOrderState,
            LocalDateTime changedAt
    ) {

        final ItemOrderHistory history = new ItemOrderHistory(
                new ItemOrderNumber(itemOrderNumber),
                ItemOrderState.valueOf(itemOrderState),
                changedAt
        );

        repository.save(history);
    }

}
