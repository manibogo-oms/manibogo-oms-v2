package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingInfo is a Querydsl query type for ShippingInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingInfo extends BeanPath<ShippingInfo> {

    private static final long serialVersionUID = 1223466800L;

    public static final QShippingInfo shippingInfo = new QShippingInfo("shippingInfo");

    public final EnumPath<ChargeType> chargeType = createEnum("chargeType", ChargeType.class);

    public final EnumPath<ShippingMethod> method = createEnum("method", ShippingMethod.class);

    public QShippingInfo(String variable) {
        super(ShippingInfo.class, forVariable(variable));
    }

    public QShippingInfo(Path<? extends ShippingInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingInfo(PathMetadata metadata) {
        super(ShippingInfo.class, metadata);
    }

}

