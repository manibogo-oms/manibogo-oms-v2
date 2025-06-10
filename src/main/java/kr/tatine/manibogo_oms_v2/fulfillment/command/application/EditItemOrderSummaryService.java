package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.common.converter.StringToDescribableEnumConverter;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNote;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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

        final ItemOrderState itemOrderState = StringToDescribableEnumConverter
                .convert(ItemOrderState.class, command.itemOrderState());

        itemOrder.changeState(itemOrderState);

        itemOrder.changeDispatchDeadline(command.dispatchDeadline());
        itemOrder.changePreferredShipsOn(command.preferredShipsOn());

        final ItemOrderNote itemOrderNote = new ItemOrderNote(
                command.purchaseMemo(), command.shippingMemo(), command.adminMemo());

        itemOrder.changeNote(itemOrderNote);
    }

}
