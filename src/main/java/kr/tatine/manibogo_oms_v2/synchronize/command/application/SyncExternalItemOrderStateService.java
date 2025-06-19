package kr.tatine.manibogo_oms_v2.synchronize.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.order.command.application.ItemOrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SyncExternalItemOrderStateService {

    private final ItemOrderRepository itemOrderRepository;

    @Transactional
    public void synchronize(@Valid SyncExternalItemOrderStateCommand command) {

        final ItemOrderState targetState = switch (command.targetState()) {
            case "구매확정" -> ItemOrderState.CONFIRMED;
            case "취소" -> ItemOrderState.CANCELLED;
            case "교환" -> ItemOrderState.REFUNDED;
            default -> throw new IllegalStateException("Unexpected value: " + command.targetState());
        };

        final ItemOrder itemOrder = itemOrderRepository
                .findById(new ItemOrderNumber(command.itemOrderNumber()))
                .orElseThrow(ItemOrderNotFoundException::new);

        itemOrder.proceedState(targetState, command.changedAt());
    }


}
