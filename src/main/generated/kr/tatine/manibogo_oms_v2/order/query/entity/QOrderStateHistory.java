package kr.tatine.manibogo_oms_v2.order.query.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderStateHistory is a Querydsl query type for OrderStateHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderStateHistory extends EntityPathBase<OrderStateHistory> {

    private static final long serialVersionUID = -2062313977L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderStateHistory orderStateHistory = new QOrderStateHistory("orderStateHistory");

    public final DateTimePath<java.time.LocalDateTime> cancelledAt = createDateTime("cancelledAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> confirmedAt = createDateTime("confirmedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> dispatchedAt = createDateTime("dispatchedAt", java.time.LocalDateTime.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QOrderNumber orderNumber;

    public final DateTimePath<java.time.LocalDateTime> placedAt = createDateTime("placedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> purchasedAt = createDateTime("purchasedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> refundedAt = createDateTime("refundedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> shippedAt = createDateTime("shippedAt", java.time.LocalDateTime.class);

    public QOrderStateHistory(String variable) {
        this(OrderStateHistory.class, forVariable(variable), INITS);
    }

    public QOrderStateHistory(Path<? extends OrderStateHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderStateHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderStateHistory(PathMetadata metadata, PathInits inits) {
        this(OrderStateHistory.class, metadata, inits);
    }

    public QOrderStateHistory(Class<? extends OrderStateHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderNumber = inits.isInitialized("orderNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QOrderNumber(forProperty("orderNumber")) : null;
    }

}

