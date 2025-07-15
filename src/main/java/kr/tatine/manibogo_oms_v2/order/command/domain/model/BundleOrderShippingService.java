package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import kr.tatine.manibogo_oms_v2.order.command.domain.exception.CannotBundleShippingException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingBundleNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BundleOrderShippingService {

    private final OrderRepository orderRepository;

    public void bundle(Order order, ShippingBundleNumber bundleNumber) {

        final List<Order> orders = orderRepository.findByShippingBundleNumber(bundleNumber);

        orders.forEach(o -> {
            if (!o.canBundleShippingWith(order)) {
                throw new CannotBundleShippingException();
            }
        });

        order.changeShippingBundleNumber(bundleNumber);
    }

}
