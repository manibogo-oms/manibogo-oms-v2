package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.Shipping;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingNotFoundException;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingOrder;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingRepository;
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

    @Transactional
    public void update(UpdateShippingOrderCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (Objects.isNull(command.orderNumber())) {
            errors.add(ValidationError.of("orderNumber", "required.orderNumber"));
        }
        if (Objects.isNull(command.orderState())) {
            errors.add(ValidationError.of("orderState", "required.orderState"));
        }

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final Shipping shipping = shippingRepository
                .findByShippingOrderNumber(command.orderNumber())
                .orElseThrow(ShippingNotFoundException::new);

        shipping.addOrders(List.of(new ShippingOrder(command.orderNumber(), command.orderState())));
    }

}
