package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.shipping.command.domain.*;
import kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingViewDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrBundleShippingService {

    private final ShippingRepository shippingRepository;

    private final OrderShippingViewDao orderShippingViewDao;

    private final ShippingTranslator translator;

    @Transactional
    public void createOrBundle(final CreateOrBundleShippingCommand command) {

        final OrderShippingView orderShippingView = orderShippingViewDao
                .findByOrderNumber(command.orderNumber())
                .orElseThrow(ShippingNotFoundException::new);

        final Shipping newShipping = translator.translate(orderShippingView);

        shippingRepository.findByShippingOrderNumber(command.orderNumber())
                        .ifPresent(shipping -> shipping.removeOrder(command.orderNumber()));

        shippingRepository.findById(orderShippingView.shippingNumber()).ifPresentOrElse(
                shipping -> shipping.bundle(newShipping),
                () -> shippingRepository.save(newShipping));
    }


}
