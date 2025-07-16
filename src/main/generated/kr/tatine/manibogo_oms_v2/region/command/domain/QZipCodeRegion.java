package kr.tatine.manibogo_oms_v2.region.command.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZipCodeRegion is a Querydsl query type for ZipCodeRegion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QZipCodeRegion extends EntityPathBase<ZipCodeRegion> {

    private static final long serialVersionUID = 1036399706L;

    public static final QZipCodeRegion zipCodeRegion = new QZipCodeRegion("zipCodeRegion");

    public final StringPath sido = createString("sido");

    public final StringPath sigungu = createString("sigungu");

    public final StringPath zipCode = createString("zipCode");

    public QZipCodeRegion(String variable) {
        super(ZipCodeRegion.class, forVariable(variable));
    }

    public QZipCodeRegion(Path<? extends ZipCodeRegion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZipCodeRegion(PathMetadata metadata) {
        super(ZipCodeRegion.class, metadata);
    }

}

