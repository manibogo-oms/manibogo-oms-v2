package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemOrderHistory is a Querydsl query type for ItemOrderHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemOrderHistory extends EntityPathBase<ItemOrderHistory> {

    private static final long serialVersionUID = -1013984982L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemOrderHistory itemOrderHistory = new QItemOrderHistory("itemOrderHistory");

    public final DateTimePath<java.time.LocalDateTime> changedAt = createDateTime("changedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QItemOrderNumber itemOrderNumber;

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState> newState = createEnum("newState", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState.class);

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState> previousState = createEnum("previousState", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState.class);

    public QItemOrderHistory(String variable) {
        this(ItemOrderHistory.class, forVariable(variable), INITS);
    }

    public QItemOrderHistory(Path<? extends ItemOrderHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemOrderHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemOrderHistory(PathMetadata metadata, PathInits inits) {
        this(ItemOrderHistory.class, metadata, inits);
    }

    public QItemOrderHistory(Class<? extends ItemOrderHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemOrderNumber = inits.isInitialized("itemOrderNumber") ? new kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.QItemOrderNumber(forProperty("itemOrderNumber")) : null;
    }

}

