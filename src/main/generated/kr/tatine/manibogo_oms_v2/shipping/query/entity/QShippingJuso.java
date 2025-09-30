package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingJuso is a Querydsl query type for ShippingJuso
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShippingJuso extends EntityPathBase<ShippingJuso> {

    private static final long serialVersionUID = 475898261L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingJuso shippingJuso = new QShippingJuso("shippingJuso");

    public final StringPath address = createString("address");

    public final StringPath admCode = createString("admCode");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath jusoCode = createString("jusoCode");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber shippingNumber;

    public final StringPath sidoName = createString("sidoName");

    public final StringPath sigunguName = createString("sigunguName");

    public QShippingJuso(String variable) {
        this(ShippingJuso.class, forVariable(variable), INITS);
    }

    public QShippingJuso(Path<? extends ShippingJuso> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingJuso(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingJuso(PathMetadata metadata, PathInits inits) {
        this(ShippingJuso.class, metadata, inits);
    }

    public QShippingJuso(Class<? extends ShippingJuso> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shippingNumber = inits.isInitialized("shippingNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QShippingNumber(forProperty("shippingNumber")) : null;
    }

}

