package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.Shipping;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingNotFoundException;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingOrder;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingRepository;
import kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingViewDao;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateShippingOrderService {

    private final ShippingRepository shippingRepository;

    private final OrderShippingViewDao orderShippingViewDao;

    @Transactional
    public void update(UpdateShippingOrderCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (Objects.isNull(command.orderNumber())) {
            errors.add(ValidationError.of("orderNumber", "required.orderNumber"));
        }

        final OrderShippingView orderShippingView = orderShippingViewDao
                .findByOrderNumber(command.orderNumber())
                .orElseThrow(ShippingNotFoundException::new);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final Shipping shipping = shippingRepository
                .findByShippingOrderNumber(command.orderNumber())
                .orElseThrow(ShippingNotFoundException::new);

        shipping.addOrders(List.of(new ShippingOrder(command.orderNumber(), orderShippingView.orderState(), orderShippingView.productNumber(), orderShippingView.amount())));
    }

}
