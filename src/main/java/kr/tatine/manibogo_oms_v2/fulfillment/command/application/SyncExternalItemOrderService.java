package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order.ItemOrder;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order.ItemOrderRepository;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.Option;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionId;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionRepository;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.Order;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.OrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.OrderRepository;
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

        if (itemOrderRepository.existsById(command.itemOrderNumber())) {
            throw new ItemOrderAlreadyPlacedException();
        }

        final ItemOrder itemOrder = new ItemOrder(
                command.itemOrderNumber(),
                getOrderNumber(command),
                getProductNumber(command),
                getOptionIds(command),
                command.amount(),
                command.totalPrice(),
                command.shippingInfo(),
                command.dispatchDeadline());

        itemOrderRepository.save(itemOrder);
    }

    private OrderNumber getOrderNumber(SyncExternalItemOrderCommand command) {
        if (!orderRepository.existsById(command.orderNumber())) {
            orderRepository.save(createNewOrder(command));
        }
        return command.orderNumber();
    }

    private Order createNewOrder(SyncExternalItemOrderCommand command) {
        return new Order(command.orderNumber(), command.customer(), command.recipient(), command.salesChannel());
    }

    private ProductNumber getProductNumber(SyncExternalItemOrderCommand command) {
        if (!productRepository.existsById(command.productNumber())) {
            productRepository.save(createNewProduct(command));
        }

        return command.productNumber();
    }

    private Product createNewProduct(SyncExternalItemOrderCommand command) {
        return new Product(command.productNumber(), command.productName(), Priority.createHighest(productRepository));
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
                optionRepository.save(new Option(optionId, command.productNumber(), tokens[1]));
            }

            optionIds.add(optionId);
        }

        return optionIds;
    }
}
