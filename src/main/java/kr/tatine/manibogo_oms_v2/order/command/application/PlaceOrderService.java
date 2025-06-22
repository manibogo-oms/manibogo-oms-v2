package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.Validator;
import kr.tatine.manibogo_oms_v2.common.model.Address;
import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.common.model.Option;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.*;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.ItemOrderRepository;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import kr.tatine.manibogo_oms_v2.product.command.application.ProductNotFoundException;
import kr.tatine.manibogo_oms_v2.product.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final VariantRepository variantRepository;

    private final ItemOrderRepository itemOrderRepository;

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

        final ItemOrder itemOrder = createItemOrder(command, productNumber, orderNumber);
        itemOrderRepository.save(itemOrder);
    }

    private ItemOrder createItemOrder(PlaceOrderCommand command, ProductNumber productNumber, OrderNumber orderNumber) {
        final ItemOrderNumber itemOrderNumber = ItemOrderNumber.random();

        final List<VariantId> variantIds = command.options()
                .stream()
                .map(option -> new VariantId(new Option(option.key(), option.value()), productNumber))
                .toList();

        variantIds.forEach(variantId -> {
            if (!variantRepository.existsById(variantId)) {
                variantRepository.save(new Variant(variantId));
            }
        });

        final ShippingInfo shippingInfo = new ShippingInfo(command.shippingMethod(), command.shippingChargeType());

        final ItemOrderNote itemOrderNote = new ItemOrderNote(command.purchaseMemo(), command.shippingMemo(), command.adminMemo());

        final ItemOrder itemOrder = ItemOrder.place(
                itemOrderNumber,
                orderNumber,
                LocalDateTime.now(),
                productNumber,
                variantIds,
                command.amount(),
                new Money(command.totalPrice()),
                shippingInfo,
                command.dispatchDeadline()
        );
        itemOrder.changeNote(itemOrderNote);

        return itemOrder;
    }

    private Order createOrder(PlaceOrderCommand command, OrderNumber orderNumber) {
        final Customer customer = new Customer(
                command.customerName(), new PhoneNumber(command.customerTel()), command.customerMemo());

        final Address address = new Address(
                command.recipientAddress1(), command.recipientAddress2(), command.recipientZipCode());

        final Recipient recipient = new Recipient(
                command.recipientName(), new PhoneNumber(command.recipientTel1()), new PhoneNumber(command.recipientTel2()), address);

        return new Order(orderNumber, customer, recipient, SalesChannel.LOCAL);
    }

}
