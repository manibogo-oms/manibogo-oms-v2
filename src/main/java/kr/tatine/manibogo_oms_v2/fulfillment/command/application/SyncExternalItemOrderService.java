package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order.*;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.Option;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionId;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionRepository;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.*;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.Priority;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.Product;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncExternalItemOrderService {

    private final OrderRepository orderRepository;

    private final ItemOrderRepository itemOrderRepository;

    private final ProductRepository productRepository;

    private final OptionRepository optionRepository;


    @Transactional
    public void synchronize(SyncExternalItemOrderCommand command) {

        final ItemOrderNumber itemOrderNumber = new ItemOrderNumber(command.itemOrderNumber());

        if (itemOrderRepository.existsById(itemOrderNumber)) {
            throw new ItemOrderAlreadyPlacedException();
        }

        final ItemOrder itemOrder = new ItemOrder(
                itemOrderNumber,
                getOrderNumber(command),
                getProductNumber(command),
                getOptionIds(command),
                command.amount(),
                command.totalPrice(),
                new ShippingInfo(
                        ShippingMethod.fromDescription(command.shippingMethod()),
                        ChargeType.fromDescription(command.shippingChargeType())),
                command.dispatchDeadline());

        itemOrderRepository.save(itemOrder);
    }

    private OrderNumber getOrderNumber(SyncExternalItemOrderCommand command) {
        final OrderNumber orderNumber = new OrderNumber(command.orderNumber());

        if (!orderRepository.existsById(orderNumber)) {
            orderRepository.save(createNewOrder(command));
        }
        return orderNumber;
    }

    private ProductNumber getProductNumber(SyncExternalItemOrderCommand command) {
        final ProductNumber productNumber = new ProductNumber(command.productNumber());

        if (!productRepository.existsById(productNumber)) {
            productRepository.save(createNewProduct(command));
        }

        return productNumber;
    }


    private List<OptionId> getOptionIds(SyncExternalItemOrderCommand command) {

        final String[] strings = command.productOption().trim().split("/");

        final List<OptionId> optionIds = new ArrayList<>();

        for (String string : strings) {
            final String[] tokens = string.trim().split(": ");

            if (tokens.length != 2) {
                throw new InvalidOptionFormatException();
            }

            final OptionId optionId = new OptionId(tokens[0], tokens[1]);

            if (!optionRepository.existsById(optionId)) {
                optionRepository.save(new Option(optionId, new ProductNumber(command.productNumber()), tokens[1]));
            }

            optionIds.add(optionId);
        }

        return optionIds;
    }

    private Product createNewProduct(SyncExternalItemOrderCommand command) {
        return new Product(new ProductNumber(command.productNumber()), command.productName(), Priority.createHighest(productRepository));
    }

    private Order createNewOrder(SyncExternalItemOrderCommand command) {
        return new Order(
                new OrderNumber(command.orderNumber()),
                createCustomer(command),
                createRecipient(command),
                SalesChannel.fromDescription(command.salesChannel()));
    }

    private Customer createCustomer(SyncExternalItemOrderCommand command) {
        return new Customer(
                command.customerName(),
                new PhoneNumber(command.customerPhoneNumber()));
    }

    private Recipient createRecipient(SyncExternalItemOrderCommand command) {
        return new Recipient(
                command.productName(),
                new PhoneNumber(command.recipientPhoneNumber1()),
                new PhoneNumber(command.recipientPhoneNumber2()),
                new Address(
                        command.recipientAddress1(),
                        command.recipientAddress2(),
                        command.recipientZipcode()));
    }
}
