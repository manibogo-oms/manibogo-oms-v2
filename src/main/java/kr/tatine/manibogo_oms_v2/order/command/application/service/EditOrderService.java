package kr.tatine.manibogo_oms_v2.order.command.application.service;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.EditOrderDetailCommand;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.EditOrderSummaryCommand;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.EditOrderSummaryCommandValidator;
import kr.tatine.manibogo_oms_v2.order.command.application.exception.OrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.application.validator.EditOrderDetailCommandValidator;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Customer;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Memo;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.ShippingInfo;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditOrderService {

    private final OrderRepository orderRepository;

//    private final BundleOrderShippingService bundleOrderShippingService;

    @Transactional
    public void editSummary(EditOrderSummaryCommand command) {

        final List<ValidationError> errors = EditOrderSummaryCommandValidator.validate(command);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final Order order = findOrder(command.orderNumber());

        order.changeState(command.orderState(), LocalDateTime.now());
        order.changeDispatchDeadline(command.dispatchDeadline());
        order.changePreferredShippingDate(command.preferredShippingDate());
        order.changeMemo(createMemo(command));
    }

    @Transactional
    public void editDetail(EditOrderDetailCommand command) {

        final List<ValidationError> errors = EditOrderDetailCommandValidator.validate(command);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final Order order = findOrder(command.orderNumber());

        order.changeState(command.orderState(), LocalDateTime.now());
        order.changeDispatchDeadline(command.dispatchDeadline());
        order.changePreferredShippingDate(command.preferredShippingDate());
        order.changeMemo(new Memo(command.purchaseMemo(), command.shippingMemo(), command.adminMemo()));

        final Customer customer = new Customer(command.customerName(), new PhoneNumber(command.customerTel()), command.customerMessage());

        order.changeShippingInfo(createShippingInfo(command));
        order.changeRecipient(createRecipient(command));

        order.changeCustomer(customer);

//        bundleOrderShippingService.bundle(order, new ShippingBundleNumber(command.shippingBundleNumber()));
    }

    private static ShippingInfo createShippingInfo(EditOrderDetailCommand command) {
        return new ShippingInfo(
                new ShippingNumber(command.shippingBundleNumber()),
                command.shippingMethod(),
                command.shippingChargeType());
    }

    private Recipient createRecipient(EditOrderDetailCommand command) {
        final Address address = new Address(
                command.recipientAddress1(), command.recipientAddress2(), command.recipientZipCode());
        return new Recipient(command.recipientName(), new PhoneNumber(command.recipientTel1()), new PhoneNumber(command.recipientTel2()), address);
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
