package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.common.converter.StringToDescribableEnumConverter;
import kr.tatine.manibogo_oms_v2.common.converter.StringToOptionIdListConvertor;
import kr.tatine.manibogo_oms_v2.common.model.Address;
import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.Option;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionId;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionRepository;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.Order;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.*;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository.ItemOrderRepository;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository.OrderRepository;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.Priority;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.Product;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductRepository;
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

    private final OptionRepository optionRepository;


    @Transactional
    public void synchronize(@Valid ExternalItemOrderRequest externalItemOrder) {

        final ItemOrderNumber itemOrderNumber = new ItemOrderNumber(externalItemOrder.itemOrderNumber());

        if (itemOrderRepository.existsById(itemOrderNumber)) {
            throw new ItemOrderAlreadyPlacedException();
        }

        final ItemOrder itemOrder = new ItemOrder(
                itemOrderNumber,
                getOrderNumber(externalItemOrder),
                getProductNumber(externalItemOrder),
                getOptionIds(externalItemOrder),
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


    private List<OptionId> getOptionIds(ExternalItemOrderRequest command) {

        return StringToOptionIdListConvertor
                .convert(command.optionInfo())
                .stream()
                .peek(optionId -> {
                    if (!optionRepository.existsById(optionId)) {
                        optionRepository.save(new Option(optionId, new ProductNumber(command.productNumber())));
                    }
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
                new PhoneNumber(command.customerPhoneNumber()));
    }

    private Recipient createRecipient(ExternalItemOrderRequest command) {
        return new Recipient(
                command.productName(),
                new PhoneNumber(command.recipientPhoneNumber1()),
                new PhoneNumber(command.recipientPhoneNumber2()),
                new Address(
                        command.recipientAddress1(),
                        command.recipientAddress2(),
                        command.recipientAddressZipcode()));
    }
}
