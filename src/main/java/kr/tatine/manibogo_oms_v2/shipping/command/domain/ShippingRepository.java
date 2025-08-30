package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, ShippingNumber> { }
