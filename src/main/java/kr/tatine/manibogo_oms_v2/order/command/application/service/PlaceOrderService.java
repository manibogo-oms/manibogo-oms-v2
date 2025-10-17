package kr.tatine.manibogo_oms_v2.order.command.application.service;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.PlaceOrderCommand;
import kr.tatine.manibogo_oms_v2.order.command.application.validator.PlaceOrderCommandValidator;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.*;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import kr.tatine.manibogo_oms_v2.product.command.application.ProductNotFoundException;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Transactional
    public void placeOrder(PlaceOrderCommand command) {

        final List<ValidationError> errors = PlaceOrderCommandValidator.validate(command);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final ProductNumber productNumber = new ProductNumber(command.productNumber());

        if (!productRepository.existsById(productNumber)) {
            throw new ProductNotFoundException();
        }

        final OrderNumber orderNumber = OrderNumber.random();

        final Order order = createOrder(command, orderNumber);
        orderRepository.save(order);
    }

    private Order createOrder(PlaceOrderCommand command, OrderNumber orderNumber) {
        final OrderProduct orderProduct = createOrderProduct(command);

        final Customer customer = new Customer(
                command.customerName(), new PhoneNumber(command.customerTel()), command.customerMessage());

        final Address address = new Address(
                command.recipientAddress1(), command.recipientAddress2(), command.recipientZipCode());

        final Memo memo = new Memo(command.purchaseMemo(), command.shippingMemo(), command.adminMemo());

        final ShippingInfo shippingInfo = new ShippingInfo(ShippingNumber.random(), command.shippingMethod(), command.shippingChargeType());

        final Recipient recipient = new Recipient(command.recipientName(), new PhoneNumber(command.recipientTel1()), new PhoneNumber(command.recipientTel2()), address);

        return new Order(orderNumber, customer, recipient, SalesChannel.LOCAL, orderProduct, shippingInfo, memo, LocalDateTime.now(), command.dispatchDeadline(), command.preferredShippingDate());
    }

    private OrderProduct createOrderProduct(PlaceOrderCommand command) {

        final List<Option> options = command.options()
                .stream()
                .map(e -> new Option(e.key(), e.value()))
                .toList();

        return new OrderProduct(
                new ProductNumber(command.productNumber()),
                options,
                command.amount(),
                new Money(command.totalPrice())
        );
    }

}
