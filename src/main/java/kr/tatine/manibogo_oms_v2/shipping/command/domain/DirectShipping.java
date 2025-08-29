package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Entity;

@Entity
public class DirectShipping extends Shipping {

    @Override
    public void complete() {

    }
}
