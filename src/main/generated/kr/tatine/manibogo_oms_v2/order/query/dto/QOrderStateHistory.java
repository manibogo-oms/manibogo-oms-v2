package kr.tatine.manibogo_oms_v2.order.query.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderStateHistory is a Querydsl query type for OrderStateHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderStateHistory extends EntityPathBase<OrderStateHistory> {

    private static final long serialVersionUID = 1514695713L;

    public static final QOrderStateHistory orderStateHistory = new QOrderStateHistory("orderStateHistory");

    public final DateTimePath<java.time.LocalDateTime> cancelledAt = createDateTime("cancelledAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> confirmedAt = createDateTime("confirmedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> dispatchedAt = createDateTime("dispatchedAt", java.time.LocalDateTime.class);

    public final StringPath orderNumber = createString("orderNumber");

    public final DateTimePath<java.time.LocalDateTime> placedAt = createDateTime("placedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> purchasedAt = createDateTime("purchasedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> refundedAt = createDateTime("refundedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> shippedAt = createDateTime("shippedAt", java.time.LocalDateTime.class);

    public QOrderStateHistory(String variable) {
        super(OrderStateHistory.class, forVariable(variable));
    }

    public QOrderStateHistory(Path<? extends OrderStateHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderStateHistory(PathMetadata metadata) {
        super(OrderStateHistory.class, metadata);
    }

}

