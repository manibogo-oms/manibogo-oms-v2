package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.variant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVariantId is a Querydsl query type for VariantId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QVariantId extends BeanPath<VariantId> {

    private static final long serialVersionUID = 2012896539L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVariantId variantId = new QVariantId("variantId");

    public final kr.tatine.manibogo_oms_v2.common.model.QOption option;

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.QProductNumber productNumber;

    public QVariantId(String variable) {
        this(VariantId.class, forVariable(variable), INITS);
    }

    public QVariantId(Path<? extends VariantId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVariantId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVariantId(PathMetadata metadata, PathInits inits) {
        this(VariantId.class, metadata, inits);
    }

    public QVariantId(Class<? extends VariantId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.option = inits.isInitialized("option") ? new kr.tatine.manibogo_oms_v2.common.model.QOption(forProperty("option")) : null;
        this.productNumber = inits.isInitialized("productNumber") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.QProductNumber(forProperty("productNumber")) : null;
    }

}

