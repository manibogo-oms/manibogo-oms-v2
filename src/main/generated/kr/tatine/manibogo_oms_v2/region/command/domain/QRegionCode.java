package kr.tatine.manibogo_oms_v2.region.command.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRegionCode is a Querydsl query type for RegionCode
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRegionCode extends BeanPath<RegionCode> {

    private static final long serialVersionUID = 1539435241L;

    public static final QRegionCode regionCode = new QRegionCode("regionCode");

    public final StringPath code = createString("code");

    public QRegionCode(String variable) {
        super(RegionCode.class, forVariable(variable));
    }

    public QRegionCode(Path<? extends RegionCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegionCode(PathMetadata metadata) {
        super(RegionCode.class, metadata);
    }

}

