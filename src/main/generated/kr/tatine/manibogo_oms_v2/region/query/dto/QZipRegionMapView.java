package kr.tatine.manibogo_oms_v2.region.query.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QZipRegionMapView is a Querydsl query type for ZipRegionMapView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QZipRegionMapView extends EntityPathBase<ZipRegionMapView> {

    private static final long serialVersionUID = -226659200L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QZipRegionMapView zipRegionMapView = new QZipRegionMapView("zipRegionMapView");

    public final QZipRegionCode code;

    public QZipRegionMapView(String variable) {
        this(ZipRegionMapView.class, forVariable(variable), INITS);
    }

    public QZipRegionMapView(Path<? extends ZipRegionMapView> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QZipRegionMapView(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QZipRegionMapView(PathMetadata metadata, PathInits inits) {
        this(ZipRegionMapView.class, metadata, inits);
    }

    public QZipRegionMapView(Class<? extends ZipRegionMapView> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.code = inits.isInitialized("code") ? new QZipRegionCode(forProperty("code")) : null;
    }

}

