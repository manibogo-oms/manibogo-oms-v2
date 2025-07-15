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

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QCustomer customer;

    public final DatePath<java.time.LocalDate> dispatchDeadline = createDate("dispatchDeadline", java.time.LocalDate.class);

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QMemo memo;

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QOrderNumber number;

    public final DateTimePath<java.time.LocalDateTime> placedAt = createDateTime("placedAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> preferredShippingDate = createDate("preferredShippingDate", java.time.LocalDate.class);

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QOrderProduct product;

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QRecipient recipient;

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel> salesChannel = createEnum("salesChannel", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel.class);

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QShipping shipping;

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QShippingBundleNumber shippingBundleNumber;

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState> state = createEnum("state", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState.class);

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QTrackingInfo trackingInfo;

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
        this.customer = inits.isInitialized("customer") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.memo = inits.isInitialized("memo") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QMemo(forProperty("memo")) : null;
        this.number = inits.isInitialized("number") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QOrderNumber(forProperty("number")) : null;
        this.product = inits.isInitialized("product") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QOrderProduct(forProperty("product"), inits.get("product")) : null;
        this.recipient = inits.isInitialized("recipient") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QRecipient(forProperty("recipient"), inits.get("recipient")) : null;
        this.shipping = inits.isInitialized("shipping") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QShipping(forProperty("shipping")) : null;
        this.shippingBundleNumber = inits.isInitialized("shippingBundleNumber") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QShippingBundleNumber(forProperty("shippingBundleNumber")) : null;
        this.trackingInfo = inits.isInitialized("trackingInfo") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QTrackingInfo(forProperty("trackingInfo")) : null;
    }

}

