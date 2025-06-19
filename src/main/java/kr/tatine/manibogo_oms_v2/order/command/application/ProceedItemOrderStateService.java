package kr.tatine.manibogo_oms_v2.order.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class ProceedItemOrderStateService {

    private final ItemOrderRepository itemOrderRepository;

    @Transactional
    public void proceed(
            @Valid ProceedItemOrderStateCommand command) {

        final ItemOrderNumber itemOrderNumber = new ItemOrderNumber(command.itemOrderNumber());

        final ItemOrder itemOrder = itemOrderRepository
                .findById(itemOrderNumber)
                .orElseThrow(ItemOrderNotFoundException::new);

        itemOrder.proceedState(command.targetState(), LocalDateTime.now());
    }

}
