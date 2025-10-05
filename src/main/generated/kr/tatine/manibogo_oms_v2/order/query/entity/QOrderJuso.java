package kr.tatine.manibogo_oms_v2.order.query.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderJuso is a Querydsl query type for OrderJuso
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderJuso extends EntityPathBase<OrderJuso> {

    private static final long serialVersionUID = -622105557L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderJuso orderJuso = new QOrderJuso("orderJuso");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final kr.tatine.manibogo_oms_v2.location.domain.juso.QJusoCode jusoCode;

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QOrderNumber orderNumber;

    public final StringPath sido = createString("sido");

    public QOrderJuso(String variable) {
        this(OrderJuso.class, forVariable(variable), INITS);
    }

    public QOrderJuso(Path<? extends OrderJuso> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderJuso(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderJuso(PathMetadata metadata, PathInits inits) {
        this(OrderJuso.class, metadata, inits);
    }

    public QOrderJuso(Class<? extends OrderJuso> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jusoCode = inits.isInitialized("jusoCode") ? new kr.tatine.manibogo_oms_v2.location.domain.juso.QJusoCode(forProperty("jusoCode")) : null;
        this.orderNumber = inits.isInitialized("orderNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QOrderNumber(forProperty("orderNumber")) : null;
    }

}

