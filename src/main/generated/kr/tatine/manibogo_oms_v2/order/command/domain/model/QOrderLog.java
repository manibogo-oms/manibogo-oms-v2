package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderLog is a Querydsl query type for OrderLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderLog extends EntityPathBase<OrderLog> {

    private static final long serialVersionUID = -214327513L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderLog orderLog = new QOrderLog("orderLog");

    public final DateTimePath<java.time.LocalDateTime> changedAt = createDateTime("changedAt", java.time.LocalDateTime.class);

    public final StringPath changedBy = createString("changedBy");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState> newState = createEnum("newState", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState.class);

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QOrderNumber orderNumber;

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState> previousState = createEnum("previousState", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState.class);

    public QOrderLog(String variable) {
        this(OrderLog.class, forVariable(variable), INITS);
    }

    public QOrderLog(Path<? extends OrderLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderLog(PathMetadata metadata, PathInits inits) {
        this(OrderLog.class, metadata, inits);
    }

    public QOrderLog(Class<? extends OrderLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderNumber = inits.isInitialized("orderNumber") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QOrderNumber(forProperty("orderNumber")) : null;
    }

}

