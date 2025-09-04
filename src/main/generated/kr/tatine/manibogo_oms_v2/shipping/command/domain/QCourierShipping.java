package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourierShipping is a Querydsl query type for CourierShipping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourierShipping extends EntityPathBase<CourierShipping> {

    private static final long serialVersionUID = -2116373287L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourierShipping courierShipping = new QCourierShipping("courierShipping");

    public final QShipping _super;

    //inherited
    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.ChargeType> chargeType;

    public final StringPath courierName = createString("courierName");

    // inherited
    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber number;

    //inherited
    public final ListPath<ShippingOrder, QShippingOrder> orders;

    // inherited
    public final kr.tatine.manibogo_oms_v2.common.model.QRecipient recipient;

    //inherited
    public final EnumPath<ShippingState> state;

    public final StringPath trackingNumber = createString("trackingNumber");

    public QCourierShipping(String variable) {
        this(CourierShipping.class, forVariable(variable), INITS);
    }

    public QCourierShipping(Path<? extends CourierShipping> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourierShipping(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourierShipping(PathMetadata metadata, PathInits inits) {
        this(CourierShipping.class, metadata, inits);
    }

    public QCourierShipping(Class<? extends CourierShipping> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QShipping(type, metadata, inits);
        this.chargeType = _super.chargeType;
        this.number = _super.number;
        this.orders = _super.orders;
        this.recipient = _super.recipient;
        this.state = _super.state;
    }

}

