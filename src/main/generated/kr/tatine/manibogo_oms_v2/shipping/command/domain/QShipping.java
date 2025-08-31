package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShipping is a Querydsl query type for Shipping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShipping extends EntityPathBase<Shipping> {

    private static final long serialVersionUID = 1193748880L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShipping shipping = new QShipping("shipping");

    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.ChargeType> chargeType = createEnum("chargeType", kr.tatine.manibogo_oms_v2.common.model.ChargeType.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber number;

    public final kr.tatine.manibogo_oms_v2.common.model.QRecipient recipient;

    public final EnumPath<ShippingState> state = createEnum("state", ShippingState.class);

    public QShipping(String variable) {
        this(Shipping.class, forVariable(variable), INITS);
    }

    public QShipping(Path<? extends Shipping> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShipping(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShipping(PathMetadata metadata, PathInits inits) {
        this(Shipping.class, metadata, inits);
    }

    public QShipping(Class<? extends Shipping> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.number = inits.isInitialized("number") ? new kr.tatine.manibogo_oms_v2.common.model.QShippingNumber(forProperty("number")) : null;
        this.recipient = inits.isInitialized("recipient") ? new kr.tatine.manibogo_oms_v2.common.model.QRecipient(forProperty("recipient"), inits.get("recipient")) : null;
    }

}

