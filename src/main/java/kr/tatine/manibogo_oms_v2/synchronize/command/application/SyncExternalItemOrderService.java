package kr.tatine.manibogo_oms_v2.synchronize.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.common.converter.StringToDescribableEnumConverter;
import kr.tatine.manibogo_oms_v2.common.converter.StringToOptionListConvertor;
import kr.tatine.manibogo_oms_v2.common.model.Address;
import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.synchronize.ui.ExternalItemOrderRequest;
import kr.tatine.manibogo_oms_v2.product.command.domain.Variant;
import kr.tatine.manibogo_oms_v2.product.command.domain.VariantId;
import kr.tatine.manibogo_oms_v2.product.command.domain.VariantRepository;
import kr.tatine.manibogo_oms_v2.order.command.application.ItemOrderAlreadyPlacedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.*;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.ItemOrderRepository;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import kr.tatine.manibogo_oms_v2.product.command.domain.Priority;
import kr.tatine.manibogo_oms_v2.product.command.domain.Product;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class SyncExternalItemOrderService {

    private final OrderRepository orderRepository;

    private final ItemOrderRepository itemOrderRepository;

    private final ProductRepository productRepository;

    private final VariantRepository variantRepository;


    @Transactional
    public void synchronize(@Valid ExternalItemOrderRequest externalItemOrder) {

        final ItemOrderNumber itemOrderNumber = new ItemOrderNumber(externalItemOrder.itemOrderNumber());

        if (itemOrderRepository.existsById(itemOrderNumber)) {
            throw new ItemOrderAlreadyPlacedException();
        }

        final ItemOrder itemOrder = ItemOrder.place(
                itemOrderNumber,
                getOrderNumber(externalItemOrder),
                externalItemOrder.itemOrderPlacedAt(),
                getProductNumber(externalItemOrder),
                getVariantIds(externalItemOrder),
                externalItemOrder.amount(),
                new Money(externalItemOrder.totalPrice()),
                new ShippingInfo(
                        StringToDescribableEnumConverter.convert(ShippingMethod.class, externalItemOrder.shippingMethod()),
                        StringToDescribableEnumConverter.convert(ChargeType.class, externalItemOrder.shippingChargeType())),
                externalItemOrder.dispatchDeadline());

        itemOrderRepository.save(itemOrder);
    }

    private OrderNumber getOrderNumber(ExternalItemOrderRequest command) {
        final OrderNumber orderNumber = new OrderNumber(command.orderNumber());

        if (!orderRepository.existsById(orderNumber)) {
            orderRepository.save(createNewOrder(command));
        }
        return orderNumber;
    }

    private ProductNumber getProductNumber(ExternalItemOrderRequest command) {
        final ProductNumber productNumber = new ProductNumber(command.productNumber());

        if (!productRepository.existsById(productNumber)) {
            productRepository.save(createNewProduct(command));
        }

        return productNumber;
    }


    private List<VariantId> getVariantIds(ExternalItemOrderRequest command) {

        return StringToOptionListConvertor
                .convert(command.optionInfo())
                .stream()
                .map(option -> {

                    final VariantId variantId = new VariantId(option, new ProductNumber(command.productNumber()));

                    if (!variantRepository.existsById(variantId)) {
                        variantRepository.save(new Variant(variantId));
                    }

                    return variantId;

                }).toList();
    }

    private Product createNewProduct(ExternalItemOrderRequest command) {
        return new Product(new ProductNumber(command.productNumber()), command.productName(), Priority.createHighest(productRepository));
    }

    private Order createNewOrder(ExternalItemOrderRequest command) {
        return new Order(
                new OrderNumber(command.orderNumber()),
                createCustomer(command),
                createRecipient(command),
                StringToDescribableEnumConverter.convert(SalesChannel.class, command.salesChannel()));
    }

    private Customer createCustomer(ExternalItemOrderRequest command) {
        return new Customer(
                command.customerName(),
                new PhoneNumber(command.customerPhoneNumber()),
                command.customerMessage()
        );
    }

    private Recipient createRecipient(ExternalItemOrderRequest command) {
        return new Recipient(
                command.recipientName(),
                new PhoneNumber(command.recipientPhoneNumber1()),
                new PhoneNumber(command.recipientPhoneNumber2()),
                new Address(
                        command.recipientAddress1(),
                        command.recipientAddress2(),
                        command.recipientAddressZipcode()));
    }
}
