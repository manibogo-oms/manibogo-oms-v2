package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.shipping.command.application.dto.OrderSnapshot;
import kr.tatine.manibogo_oms_v2.shipping.command.application.port.out.OrderSnapshotQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrBundleShippingService {

    private final ShippingRepository shippingRepository;

    private final OrderSnapshotQueryPort orderSnapshotQueryPort;

    private final ShippingFactory shippingFactory;

    @Transactional
    public void createOrBundle(final CreateOrBundleShippingCommand command) {

        final OrderSnapshot snapshot = orderSnapshotQueryPort
                .findByNumber(command.orderNumber())
                .orElseThrow(ShippingNotFoundException::new);

        final ShippingOrder shippingOrder = new ShippingOrder(
                snapshot.orderNumber(), snapshot.orderState(), snapshot.productNumber(), snapshot.amount());

        shippingRepository
                .findByShippingOrderNumber(command.orderNumber())
                .ifPresent(shipping -> shipping.removeOrder(command.orderNumber()));

        final Shipping shipping = shippingRepository
                .findById(snapshot.shippingNumber())
                .orElseGet(() -> shippingRepository.save(createShipping(snapshot)));

        shipping.bundle(snapshot.shippingMethod(), snapshot.chargeType(), snapshot.recipient());
        shipping.addOrder(shippingOrder);

        shippingRepository.save(shipping);
    }

    private Shipping createShipping(OrderSnapshot snapshot) {
        return shippingFactory.create(
                snapshot.shippingMethod(),
                snapshot.shippingNumber(),
                snapshot.chargeType(),
                snapshot.recipient(),
                snapshot.customerMessage()
        );
    }


}
