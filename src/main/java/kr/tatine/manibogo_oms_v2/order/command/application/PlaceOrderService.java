package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.model.Address;
import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.common.model.Option;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.*;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import kr.tatine.manibogo_oms_v2.product.command.application.ProductNotFoundException;
import kr.tatine.manibogo_oms_v2.product.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        final Recipient recipient = new Recipient(
                command.recipientName(), new PhoneNumber(command.recipientTel1()), new PhoneNumber(command.recipientTel2()), address);

        final Memo memo = new Memo(command.purchaseMemo(), command.shippingMemo(), command.adminMemo());

        final Shipping shipping = new Shipping(command.shippingMethod(), command.shippingChargeType(), command.dispatchDeadline(), command.preferredShippingDate());

        return new Order(orderNumber, customer, recipient, SalesChannel.LOCAL, orderProduct, shipping, memo);
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
