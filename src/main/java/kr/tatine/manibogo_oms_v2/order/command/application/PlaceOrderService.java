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
import kr.tatine.manibogo_oms_v2.order.ui.PlaceOrderForm;
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
    public void placeOrder(PlaceOrderForm form) {

        final List<ValidationError> errors = new ArrayList<>();

        if (form.getCustomerName() == null || form.getCustomerName().isBlank()) {
            errors.add(ValidationError.of("customerName", "required.order.customerName"));
        }

        if (form.getCustomerTel() == null || form.getCustomerTel().isBlank()) {
            errors.add(ValidationError.of("customerTel", "required.order.customerTel"));
        }

        if (form.getRecipientName() == null || form.getRecipientName().isBlank()) {
            errors.add(ValidationError.of("recipientName", "required.order.recipientName"));
        }

        if (form.getRecipientTel1() == null || form.getRecipientTel1().isBlank()) {
            errors.add(ValidationError.of("recipientTel1", "required.order.recipientTel1"));
        }

        if ((form.getRecipientZipCode() == null || form.getRecipientZipCode().isBlank())
                || (form.getRecipientAddress1() == null || form.getRecipientAddress1().isBlank())) {
            errors.add(ValidationError.of("recipientAddress", "required.order.recipientAddress"));
        }

        if (form.getProductNumber() == null || form.getProductNumber().isBlank()) {
            errors.add(ValidationError.of("productNumber", "required.order.productNumber"));
        }

        if (form.getAmount() == null) {
            errors.add(ValidationError.of("amount", "required.order.amount"));
        } else if (form.getAmount() < 0) {
            errors.add(ValidationError.of("amount", "min.order.amount"));
        }

        if (form.getOptions() != null) {
            for (int i = 0; i < form.getOptions().size(); i ++) {
                final PlaceOrderForm.PlaceOrderOptionForm option = form.getOptions().get(i);
                final String fieldName = "options[%d].value".formatted(i);

                if (option.getKey() == null || option.getKey().isBlank()) {
                    errors.add(ValidationError.of(fieldName, "required.order." + fieldName));
                }

                if (option.getValue() == null || option.getValue().isBlank()) {
                    errors.add(ValidationError.of(fieldName, "required.order." + fieldName));
                }
            }
        }

        if (form.getShippingMethod() == null) {
            errors.add(ValidationError.of("shippingMethod", "required.order.shippingMethod"));
        }

        if (form.getShippingChargeType() == null) {
            errors.add(ValidationError.of("shippingChargeType", "required.order.shippingChargeType"));
        }

        if (form.getDispatchDeadline() == null) {
            errors.add(ValidationError.of("dispatchDeadline", "required.order.dispatchDeadline"));
        }

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final ProductNumber productNumber = new ProductNumber(form.getProductNumber());

        if (!productRepository.existsById(productNumber)) {
            throw new ProductNotFoundException();
        }

        final OrderNumber orderNumber = OrderNumber.random();

        final Order order = createOrder(form, orderNumber);
        orderRepository.save(order);

        final ItemOrder itemOrder = createItemOrder(form, productNumber, orderNumber);
        itemOrderRepository.save(itemOrder);
    }

    private ItemOrder createItemOrder(PlaceOrderForm form, ProductNumber productNumber, OrderNumber orderNumber) {
        final ItemOrderNumber itemOrderNumber = ItemOrderNumber.random();

        final List<VariantId> variantIds = form.getOptions()
                .stream()
                .map(option -> new VariantId(new Option(option.getKey(), option.getValue()), productNumber))
                .toList();

        variantIds.forEach(variantId -> {
            if (!variantRepository.existsById(variantId)) {
                variantRepository.save(new Variant(variantId));
            }
        });

        final ShippingInfo shippingInfo = new ShippingInfo(form.getShippingMethod(), form.getShippingChargeType());

        final ItemOrderNote itemOrderNote = new ItemOrderNote(form.getPurchaseMemo(), form.getShippingMemo(), form.getAdminMemo());

        final ItemOrder itemOrder = ItemOrder.place(
                itemOrderNumber,
                orderNumber,
                LocalDateTime.now(),
                productNumber,
                variantIds,
                form.getAmount(),
                new Money(0L),
                shippingInfo,
                form.getDispatchDeadline()
        );
        itemOrder.changeNote(itemOrderNote);

        return itemOrder;
    }

    private Order createOrder(PlaceOrderForm form, OrderNumber orderNumber) {
        final Customer customer = new Customer(
                form.getCustomerName(), new PhoneNumber(form.getCustomerTel()), form.getCustomerMemo());

        final Address address = new Address(
                form.getRecipientAddress1(), form.getRecipientAddress2(), form.getRecipientZipCode());

        final Recipient recipient = new Recipient(
                form.getRecipientName(), new PhoneNumber(form.getRecipientTel1()), new PhoneNumber(form.getRecipientTel2()), address);

        return new Order(orderNumber, customer, recipient, SalesChannel.LOCAL);
    }

}
