package kr.tatine.manibogo_oms_v2.synchronize.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.common.converter.StringToDescribableEnumConverter;
import kr.tatine.manibogo_oms_v2.common.converter.StringToOptionListConvertor;
import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.order.command.application.exception.OrderAlreadyPlacedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.*;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import kr.tatine.manibogo_oms_v2.product.command.domain.Priority;
import kr.tatine.manibogo_oms_v2.product.command.domain.Product;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductRepository;
import kr.tatine.manibogo_oms_v2.synchronize.ui.ExternalItemOrderRequest;
import kr.tatine.manibogo_oms_v2.variant.command.domain.Variant;
import kr.tatine.manibogo_oms_v2.variant.command.domain.VariantId;
import kr.tatine.manibogo_oms_v2.variant.command.domain.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class SyncExternalOrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final VariantRepository variantRepository;

    @Transactional
    public void synchronize(@Valid ExternalItemOrderRequest command) {
        final OrderNumber orderNumber = new OrderNumber(command.orderNumber());

        if (orderRepository.existsById(orderNumber)) {
            throw new OrderAlreadyPlacedException();
        }

        orderRepository.save(createOrder(command));
    }

    private OrderProduct createOrderProduct(ExternalItemOrderRequest command) {
        return new OrderProduct(
                getProductNumber(command),
                getOptions(command),
                command.amount(),
                new Money(command.totalPrice())
        );
    }

    private Order createOrder(ExternalItemOrderRequest command) {
        final Customer customer = new Customer(
                command.customerName(), new PhoneNumber(command.customerPhoneNumber()), command.customerMessage());

        final Address address = new Address(
                command.recipientAddress1(), command.recipientAddress2(), command.recipientAddressZipcode());


        final Recipient recipient = new Recipient(command.recipientName(), new PhoneNumber(command.recipientPhoneNumber1()), new PhoneNumber(command.recipientPhoneNumber2()), address);

        final ShippingInfo shippingInfo = new ShippingInfo(
                new ShippingNumber(command.shippingBundleNumber()),
                StringToDescribableEnumConverter.convert(ShippingMethod.class, command.shippingMethod()),
                StringToDescribableEnumConverter.convert(ChargeType.class, command.shippingChargeType()));

        return new Order(new OrderNumber(command.orderNumber()), customer, recipient, SalesChannel.SMART_STORE, createOrderProduct(command), shippingInfo, null, command.orderPlacedAt(), command.dispatchDeadline(), null);
    }



    private ProductNumber getProductNumber(ExternalItemOrderRequest command) {
        final ProductNumber productNumber = new ProductNumber(command.productNumber());
        if (!productRepository.existsById(productNumber)) {
            productRepository.save(createProduct(command));
        }

        return productNumber;
    }


    private List<Option> getOptions(ExternalItemOrderRequest command) {

        List<Option> options = StringToOptionListConvertor
                .convert(command.optionInfo());

        options.forEach(option -> {
            final VariantId variantId = new VariantId(option, new ProductNumber(command.productNumber()));

            if (!variantRepository.existsById(variantId)) {
                variantRepository.save(new Variant(variantId));
            }
        });

        return options;

    }

    private Product createProduct(ExternalItemOrderRequest command) {
        return new Product(new ProductNumber(command.productNumber()), command.productName(), Priority.createHighest(productRepository));
    }

}
