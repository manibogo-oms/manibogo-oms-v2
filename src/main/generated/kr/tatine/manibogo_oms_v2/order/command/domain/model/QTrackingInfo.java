package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrackingInfo is a Querydsl query type for TrackingInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTrackingInfo extends BeanPath<TrackingInfo> {

    private static final long serialVersionUID = -1209714282L;

    public static final QTrackingInfo trackingInfo = new QTrackingInfo("trackingInfo");

    public final StringPath companyName = createString("companyName");

    public final StringPath trackingNumber = createString("trackingNumber");

    public QTrackingInfo(String variable) {
        super(TrackingInfo.class, forVariable(variable));
    }

    public QTrackingInfo(Path<? extends TrackingInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrackingInfo(PathMetadata metadata) {
        super(TrackingInfo.class, metadata);
    }

}

