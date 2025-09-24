package kr.tatine.manibogo_oms_v2.region.query.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZipRegionCode is a Querydsl query type for ZipRegionCode
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QZipRegionCode extends BeanPath<ZipRegionCode> {

    private static final long serialVersionUID = 1364997102L;

    public static final QZipRegionCode zipRegionCode = new QZipRegionCode("zipRegionCode");

    public final StringPath regionCode = createString("regionCode");

    public final StringPath zipCode = createString("zipCode");

    public QZipRegionCode(String variable) {
        super(ZipRegionCode.class, forVariable(variable));
    }

    public QZipRegionCode(Path<? extends ZipRegionCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZipRegionCode(PathMetadata metadata) {
        super(ZipRegionCode.class, metadata);
    }

}

