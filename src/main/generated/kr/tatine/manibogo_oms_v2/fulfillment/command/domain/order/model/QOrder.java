package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model;

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

    private static final long serialVersionUID = -1016044435L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QCustomer customer;

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QOrderNumber number;

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QRecipient recipient;

    public final EnumPath<kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel> salesChannel = createEnum("salesChannel", kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel.class);

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
        this.customer = inits.isInitialized("customer") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.number = inits.isInitialized("number") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QOrderNumber(forProperty("number")) : null;
        this.recipient = inits.isInitialized("recipient") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QRecipient(forProperty("recipient"), inits.get("recipient")) : null;
    }

}

