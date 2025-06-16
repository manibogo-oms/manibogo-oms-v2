package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVariant is a Querydsl query type for Variant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVariant extends EntityPathBase<Variant> {

    private static final long serialVersionUID = 612439944L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVariant variant = new QVariant("variant");

    public final StringPath label = createString("label");

    public final QVariantId variantId;

    public QVariant(String variable) {
        this(Variant.class, forVariable(variable), INITS);
    }

    public QVariant(Path<? extends Variant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVariant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVariant(PathMetadata metadata, PathInits inits) {
        this(Variant.class, metadata, inits);
    }

    public QVariant(Class<? extends Variant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.variantId = inits.isInitialized("variantId") ? new QVariantId(forProperty("variantId"), inits.get("variantId")) : null;
    }

}

