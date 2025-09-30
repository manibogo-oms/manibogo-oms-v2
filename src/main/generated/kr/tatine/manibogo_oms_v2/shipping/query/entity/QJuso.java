package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJuso is a Querydsl query type for Juso
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJuso extends EntityPathBase<Juso> {

    private static final long serialVersionUID = 29603527L;

    public static final QJuso juso = new QJuso("juso");

    public final StringPath address = createString("address");

    public final StringPath admCode = createString("admCode");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath jusoCode = createString("jusoCode");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public final StringPath sido = createString("sido");

    public final StringPath sigungu = createString("sigungu");

    public QJuso(String variable) {
        super(Juso.class, forVariable(variable));
    }

    public QJuso(Path<? extends Juso> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJuso(PathMetadata metadata) {
        super(Juso.class, metadata);
    }

}

