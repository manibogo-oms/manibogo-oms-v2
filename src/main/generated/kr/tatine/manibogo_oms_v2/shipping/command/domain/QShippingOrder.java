package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingOrder is a Querydsl query type for ShippingOrder
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingOrder extends BeanPath<ShippingOrder> {

    private static final long serialVersionUID = 171108254L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingOrder shippingOrder = new QShippingOrder("shippingOrder");

    public final kr.tatine.manibogo_oms_v2.common.model.QOrderNumber orderNumber;

    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.OrderState> orderState = createEnum("orderState", kr.tatine.manibogo_oms_v2.common.model.OrderState.class);

    public QShippingOrder(String variable) {
        this(ShippingOrder.class, forVariable(variable), INITS);
    }

    public QShippingOrder(Path<? extends ShippingOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingOrder(PathMetadata metadata, PathInits inits) {
        this(ShippingOrder.class, metadata, inits);
    }

    public QShippingOrder(Class<? extends ShippingOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderNumber = inits.isInitialized("orderNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QOrderNumber(forProperty("orderNumber")) : null;
    }

}

