package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
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

        final List<ValidationError> errors = new ArrayList<>();

        if (command.customerName() == null || command.customerName().isBlank()) {
            errors.add(ValidationError.of("customerName", "required.order.customerName"));
        }

        if (command.customerTel() == null || command.customerTel().isBlank()) {
            errors.add(ValidationError.of("customerTel", "required.order.customerTel"));
        }

        if (command.recipientName() == null || command.recipientName().isBlank()) {
            errors.add(ValidationError.of("recipientName", "required.order.recipientName"));
        }

        if (command.recipientTel1() == null || command.recipientTel1().isBlank()) {
            errors.add(ValidationError.of("recipientTel1", "required.order.recipientTel1"));
        }

        if ((command.recipientZipCode() == null || command.recipientZipCode().isBlank())
                || (command.recipientAddress1() == null || command.recipientAddress1().isBlank())) {
            errors.add(ValidationError.of("recipientAddress", "required.order.recipientAddress"));
        }

        if (command.productNumber() == null || command.productNumber().isBlank()) {
            errors.add(ValidationError.of("productNumber", "required.order.productNumber"));
        }

        if (command.amount() == null) {
            errors.add(ValidationError.of("amount", "required.order.amount"));
        } else if (command.amount() < 0) {
            errors.add(ValidationError.of("amount", "min.order.amount"));
        }

        if (command.totalPrice() == null) {
            errors.add(ValidationError.of("totalPrice", "required.order.totalPrice"));
        } else if (command.totalPrice() < 0) {
            errors.add(ValidationError.of("totalPrice", "min.order.totalPrice"));
        }

        if (command.options() != null) {
            for (int i = 0; i < command.options().size(); i ++) {
                final PlaceOrderCommand.PlaceOrderOptionCommand option = command.options().get(i);
                final String fieldName = "options[%d].value".formatted(i);

                if (option.key() == null || option.key().isBlank()) {
                    errors.add(ValidationError.of(fieldName, "required.order." + fieldName));
                }

                if (option.value() == null || option.value().isBlank()) {
                    errors.add(ValidationError.of(fieldName, "required.order." + fieldName));
                }
            }
        }

        if (command.shippingMethod() == null) {
            errors.add(ValidationError.of("shippingMethod", "required.order.shippingMethod"));
        }

        if (command.shippingChargeType() == null) {
            errors.add(ValidationError.of("shippingChargeType", "required.order.shippingChargeType"));
        }

        if (command.dispatchDeadline() == null) {
            errors.add(ValidationError.of("dispatchDeadline", "required.order.dispatchDeadline"));
        }

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
