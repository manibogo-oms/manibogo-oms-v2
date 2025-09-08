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

        final Shipping newShipping = shippingFactory.create(
                snapshot.shippingMethod(), snapshot.shippingNumber(), snapshot.chargeType(), snapshot.recipient());

        shippingRepository
                .findByShippingOrderNumber(command.orderNumber())
                .ifPresent(shipping -> shipping.removeOrder(command.orderNumber()));

        shippingRepository
                .findById(snapshot.shippingNumber())
                .ifPresentOrElse(
                        shipping -> shipping.bundle(newShipping),
                        () -> shippingRepository.save(newShipping)
                );
    }


}
