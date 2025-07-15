package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.common.model.Address;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.Customer;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.Memo;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.Recipient;
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
        final Order order = findOrder(command.orderNumber());

        order.changeState(command.state(), LocalDateTime.now());
        order.changeDispatchDeadline(command.dispatchDeadline());
        order.changePreferredShippingDate(command.preferredShippingDate());
        order.changeMemo(createMemo(command));
    }

    @Transactional
    public void editDetail(EditOrderDetailCommand command) {
        final Order order = findOrder(command.orderNumber());

        order.changeState(command.orderState(), LocalDateTime.now());
        order.changeDispatchDeadline(command.dispatchDeadline());
        order.changePreferredShippingDate(command.preferredShippingDate());
        order.changeMemo(new Memo(command.purchaseMemo(), command.shippingMemo(), command.adminMemo()));

        final Customer customer = new Customer(command.customerName(), new PhoneNumber(command.customerTel()), command.customerMessage());
        final Recipient recipient = createRecipient(command);

        order.changeRecipient(recipient);
        order.changeCustomer(customer);
    }

    private Recipient createRecipient(EditOrderDetailCommand command) {
        final Address address = new Address(
                command.recipientAddress1(), command.recipientAddress2(), command.recipientZipCode());

        final Recipient recipient = new Recipient(
                command.recipientName(), new PhoneNumber(command.recipientTel1()), new PhoneNumber(command.recipientTel2()), address);
        return recipient;
    }

    private Order findOrder(String orderNumber) {
        return orderRepository
                .findById(new OrderNumber(orderNumber))
                .orElseThrow(OrderNotFoundException::new);
    }

    private Memo createMemo(EditOrderSummaryCommand command) {
        return new Memo(command.purchaseMemo(), command.shippingMemo(), command.adminMemo());
    }

}
