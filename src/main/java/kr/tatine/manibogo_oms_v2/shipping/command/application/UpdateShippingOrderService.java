package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.shipping.command.application.dto.OrderSnapshot;
import kr.tatine.manibogo_oms_v2.shipping.command.application.port.out.OrderSnapshotQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.Shipping;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingNotFoundException;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingOrder;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateShippingOrderService {

    private final ShippingRepository shippingRepository;

    private final OrderSnapshotQueryPort orderSnapshotQueryPort;

    @Transactional
    public void update(UpdateShippingOrderCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (Objects.isNull(command.orderNumber())) {
            errors.add(ValidationError.of("orderNumber", "required.orderNumber"));
        }

        final OrderSnapshot orderSnapshot = orderSnapshotQueryPort
                .findByNumber(command.orderNumber())
                .orElseThrow(ShippingNotFoundException::new);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final Shipping shipping = shippingRepository
                .findByShippingOrderNumber(command.orderNumber())
                .orElseThrow(ShippingNotFoundException::new);

        shipping.addOrder(new ShippingOrder(command.orderNumber(), orderSnapshot.orderState(), orderSnapshot.productNumber(), orderSnapshot.amount()));
    }

}
