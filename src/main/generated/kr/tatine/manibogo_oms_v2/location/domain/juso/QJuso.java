package kr.tatine.manibogo_oms_v2.location.domain.juso;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJuso is a Querydsl query type for Juso
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJuso extends EntityPathBase<Juso> {

    private static final long serialVersionUID = -735054910L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJuso juso = new QJuso("juso");

    public final StringPath address = createString("address");

    public final StringPath admCode = createString("admCode");

    public final QJusoCode code;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QJusoIntegration integration;

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public final StringPath sido = createString("sido");

    public final StringPath sigungu = createString("sigungu");

    public QJuso(String variable) {
        this(Juso.class, forVariable(variable), INITS);
    }

    public QJuso(Path<? extends Juso> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJuso(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJuso(PathMetadata metadata, PathInits inits) {
        this(Juso.class, metadata, inits);
    }

    public QJuso(Class<? extends Juso> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.code = inits.isInitialized("code") ? new QJusoCode(forProperty("code")) : null;
        this.integration = inits.isInitialized("integration") ? new QJusoIntegration(forProperty("integration")) : null;
    }

}

