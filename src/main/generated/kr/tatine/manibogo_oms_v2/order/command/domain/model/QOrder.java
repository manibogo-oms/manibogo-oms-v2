package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 131908317L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final QCustomer customer;

    public final DatePath<java.time.LocalDate> dispatchDeadline = createDate("dispatchDeadline", java.time.LocalDate.class);

    public final QMemo memo;

    public final kr.tatine.manibogo_oms_v2.common.model.QOrderNumber number;

    public final DatePath<java.time.LocalDate> preferredShippingDate = createDate("preferredShippingDate", java.time.LocalDate.class);

    public final QOrderProduct product;

    public final kr.tatine.manibogo_oms_v2.common.model.QRecipient recipient;

    public final EnumPath<SalesChannel> salesChannel = createEnum("salesChannel", SalesChannel.class);

    public final QShippingInfo shippingInfo;

    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.OrderState> state = createEnum("state", kr.tatine.manibogo_oms_v2.common.model.OrderState.class);

    public final QTrackingInfo trackingInfo;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.memo = inits.isInitialized("memo") ? new QMemo(forProperty("memo")) : null;
        this.number = inits.isInitialized("number") ? new kr.tatine.manibogo_oms_v2.common.model.QOrderNumber(forProperty("number")) : null;
        this.product = inits.isInitialized("product") ? new QOrderProduct(forProperty("product"), inits.get("product")) : null;
        this.recipient = inits.isInitialized("recipient") ? new kr.tatine.manibogo_oms_v2.common.model.QRecipient(forProperty("recipient"), inits.get("recipient")) : null;
        this.shippingInfo = inits.isInitialized("shippingInfo") ? new QShippingInfo(forProperty("shippingInfo"), inits.get("shippingInfo")) : null;
        this.trackingInfo = inits.isInitialized("trackingInfo") ? new QTrackingInfo(forProperty("trackingInfo")) : null;
    }

}

