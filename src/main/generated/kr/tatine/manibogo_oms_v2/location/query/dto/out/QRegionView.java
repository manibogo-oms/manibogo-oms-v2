package kr.tatine.manibogo_oms_v2.location.query.dto.out;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import kr.tatine.manibogo_oms_v2.location.domain.region.Region;


/**
 * QRegionView is a Querydsl query type for RegionView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegionView extends EntityPathBase<Region> {

    private static final long serialVersionUID = 1795233005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegionView regionView = new QRegionView("regionView");

    public final kr.tatine.manibogo_oms_v2.location.command.domain.QRegionCode code;

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final kr.tatine.manibogo_oms_v2.location.command.domain.QRegionCode parentCode;

    public final StringPath shortName = createString("shortName");

    public QRegionView(String variable) {
        this(Region.class, forVariable(variable), INITS);
    }

    public QRegionView(Path<? extends Region> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegionView(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegionView(PathMetadata metadata, PathInits inits) {
        this(Region.class, metadata, inits);
    }

    public QRegionView(Class<? extends Region> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.code = inits.isInitialized("code") ? new kr.tatine.manibogo_oms_v2.location.command.domain.QRegionCode(forProperty("code")) : null;
        this.parentCode = inits.isInitialized("parentCode") ? new kr.tatine.manibogo_oms_v2.location.command.domain.QRegionCode(forProperty("parentCode")) : null;
    }

}

