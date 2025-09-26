package kr.tatine.manibogo_oms_v2.shipping.query.dto.out;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingJusoView is a Querydsl query type for ShippingJusoView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShippingJusoView extends EntityPathBase<ShippingJusoView> {

    private static final long serialVersionUID = 660974324L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingJusoView shippingJusoView = new QShippingJusoView("shippingJusoView");

    public final StringPath address = createString("address");

    public final StringPath admCode = createString("admCode");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath jusoCode = createString("jusoCode");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber shippingNumber;

    public final StringPath sidoName = createString("sidoName");

    public final StringPath sigunguName = createString("sigunguName");

    public QShippingJusoView(String variable) {
        this(ShippingJusoView.class, forVariable(variable), INITS);
    }

    public QShippingJusoView(Path<? extends ShippingJusoView> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingJusoView(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingJusoView(PathMetadata metadata, PathInits inits) {
        this(ShippingJusoView.class, metadata, inits);
    }

    public QShippingJusoView(Class<? extends ShippingJusoView> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shippingNumber = inits.isInitialized("shippingNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QShippingNumber(forProperty("shippingNumber")) : null;
    }

}

