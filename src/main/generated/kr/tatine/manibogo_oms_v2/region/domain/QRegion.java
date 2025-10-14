package kr.tatine.manibogo_oms_v2.region.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegion is a Querydsl query type for Region
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegion extends EntityPathBase<Region> {

    private static final long serialVersionUID = -1475351233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegion region = new QRegion("region");

    public final QRegionCode code;

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final QRegionCode parentCode;

    public final StringPath shortName = createString("shortName");

    public QRegion(String variable) {
        this(Region.class, forVariable(variable), INITS);
    }

    public QRegion(Path<? extends Region> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegion(PathMetadata metadata, PathInits inits) {
        this(Region.class, metadata, inits);
    }

    public QRegion(Class<? extends Region> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.code = inits.isInitialized("code") ? new QRegionCode(forProperty("code")) : null;
        this.parentCode = inits.isInitialized("parentCode") ? new QRegionCode(forProperty("parentCode")) : null;
    }

}

