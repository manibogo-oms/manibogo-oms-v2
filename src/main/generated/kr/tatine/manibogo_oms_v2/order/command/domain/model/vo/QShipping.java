package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShipping is a Querydsl query type for Shipping
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShipping extends BeanPath<Shipping> {

    private static final long serialVersionUID = -1127738926L;

    public static final QShipping shipping = new QShipping("shipping");

    public final EnumPath<ChargeType> chargeType = createEnum("chargeType", ChargeType.class);

    public final DatePath<java.time.LocalDate> dispatchDeadline = createDate("dispatchDeadline", java.time.LocalDate.class);

    public final EnumPath<ShippingMethod> method = createEnum("method", ShippingMethod.class);

    public final DatePath<java.time.LocalDate> preferredShippingDate = createDate("preferredShippingDate", java.time.LocalDate.class);

    public QShipping(String variable) {
        super(Shipping.class, forVariable(variable));
    }

    public QShipping(Path<? extends Shipping> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShipping(PathMetadata metadata) {
        super(Shipping.class, metadata);
    }

}

