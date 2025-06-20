package kr.tatine.manibogo_oms_v2.order.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderNote;
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
public class EditItemOrderSummaryService {

    private final ItemOrderRepository itemOrderRepository;

    @Transactional
    public void edit(@Valid EditItemOrderSummaryCommand command) {

        final ItemOrderNumber itemOrderNumber =
                new ItemOrderNumber(command.itemOrderNumber());

        final ItemOrder itemOrder = itemOrderRepository
                .findById(itemOrderNumber)
                .orElseThrow(ItemOrderNotFoundException::new);

        itemOrder.changeState(command.itemOrderState(), LocalDateTime.now());

        itemOrder.changeDispatchDeadline(command.dispatchDeadline());
        itemOrder.changePreferredShipsOn(command.preferredShipsOn());

        final ItemOrderNote itemOrderNote = new ItemOrderNote(
                command.purchaseMemo(), command.shippingMemo(), command.adminMemo());

        itemOrder.changeNote(itemOrderNote);
    }

}
