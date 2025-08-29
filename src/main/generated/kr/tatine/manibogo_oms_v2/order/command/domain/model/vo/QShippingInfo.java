package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingInfo is a Querydsl query type for ShippingInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingInfo extends BeanPath<ShippingInfo> {

    private static final long serialVersionUID = 336179616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingInfo shippingInfo = new QShippingInfo("shippingInfo");

    public final kr.tatine.manibogo_oms_v2.region.command.domain.QAddress address;

    public final EnumPath<ChargeType> chargeType = createEnum("chargeType", ChargeType.class);

    public final EnumPath<ShippingMethod> method = createEnum("method", ShippingMethod.class);

    public final StringPath recipientName = createString("recipientName");

    public final kr.tatine.manibogo_oms_v2.common.model.QPhoneNumber recipientTel1;

    public final kr.tatine.manibogo_oms_v2.common.model.QPhoneNumber recipientTel2;

    public QShippingInfo(String variable) {
        this(ShippingInfo.class, forVariable(variable), INITS);
    }

    public QShippingInfo(Path<? extends ShippingInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingInfo(PathMetadata metadata, PathInits inits) {
        this(ShippingInfo.class, metadata, inits);
    }

    public QShippingInfo(Class<? extends ShippingInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new kr.tatine.manibogo_oms_v2.region.command.domain.QAddress(forProperty("address")) : null;
        this.recipientTel1 = inits.isInitialized("recipientTel1") ? new kr.tatine.manibogo_oms_v2.common.model.QPhoneNumber(forProperty("recipientTel1")) : null;
        this.recipientTel2 = inits.isInitialized("recipientTel2") ? new kr.tatine.manibogo_oms_v2.common.model.QPhoneNumber(forProperty("recipientTel2")) : null;
    }

}

